import base64
import logging
from time import sleep
import datetime

import yaml
from semaphore_client.semaphore_helper import SemaphoreHelper

yaml.Dumper.ignore_aliases = lambda *args: True

logger = logging.getLogger(__name__)
if not getattr(logger, 'handler_set', None):
    logger.setLevel(logging.INFO)
    h = logging.StreamHandler()
    formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
    h.setFormatter(formatter)
    logger.addHandler(h)
    logger.handler_set = True


def build_yml_inventory(vms):
    # loader = DataLoader()
    # inventory = InventoryManager(loader=loader)
    # variable_manager = VariableManager()
    inventory = {}
    all = {}
    vars = {'ansible_ssh_common_args': '-o StrictHostKeyChecking=no'}
    vars['ansible_ssh_user'] = vms[0].node_template.properties['user_name']
    children = {}
    for vm in vms:
        attributes = vm.node_template.attributes
        role = attributes['role']
        public_ip = attributes['public_ip']
        if role not in children:
            hosts = {}
        else:
            hosts = children[role]
        host = {}
        host[public_ip] = vars
        hosts['hosts'] = host
        children[role] = hosts
        # inventory.add_group(role)
        # inventory.add_host(public_ip,group=role)
    all['children'] = children
    inventory['all'] = all
    return inventory


def get_private_key(vms):
    private_key = vms[0].node_template.attributes['user_key_pair']['keys']['private_key']
    return base64.b64decode(private_key).decode('utf-8').replace(r'\n', '\n')


class AnsibleService:

    def __init__(self, semaphore_base_url=None, semaphore_username=None, semaphore_password=None):
        self.semaphore_base_url = semaphore_base_url
        self.semaphore_username = semaphore_username
        self.semaphore_password = semaphore_password
        self.semaphore_helper = SemaphoreHelper(self.semaphore_base_url, self.semaphore_username,
                                                self.semaphore_password)
        self.repository_id = None
        self.template_id = None

    def execute(self, nodes_pair):
        vms = nodes_pair[0]
        application = nodes_pair[1]
        name = application.name
        desired_state = None
        interfaces = application.node_template.interfaces
        if 'current_state' in application.node_template.attributes:
            current_state = application.node_template.attributes['current_state']
        if 'desired_state' in application.node_template.attributes:
            desired_state = application.node_template.attributes['desired_state']

        if desired_state:
            now = datetime.datetime.now()
            project_id = self.semaphore_helper.create_project(application.name + '_' + str(now))
            inventory_contents = yaml.dump(build_yml_inventory(vms), default_flow_style=False)
            private_key = get_private_key(vms)
            key_id = self.semaphore_helper.create_ssh_key(application.name, project_id, private_key)
            inventory_id = self.semaphore_helper.create_inventory(application.name, project_id, key_id,
                                                                  inventory_contents)
            if 'RUNNING' == desired_state:
                standard = interfaces['Standard']
                create = standard['create']
                inputs = create['inputs']
                git_url = inputs['repository']
                playbook_names = inputs['resources']
                for playbook_name in playbook_names:
                    task_id = self.run_task(name, project_id, key_id, git_url, inventory_id, playbook_name)
                    if self.semaphore_helper.get_task(project_id, task_id).status != 'success':
                        break

                if self.semaphore_helper.get_task(project_id, task_id).status == 'success':
                    configure = standard['configure']
                    inputs = configure['inputs']
                    git_url = inputs['repository']
                    playbook_names = inputs['resources']
                    for playbook_name in playbook_names:
                        task_id = self.run_task(name, project_id, key_id, git_url, inventory_id, playbook_name)
                        if self.semaphore_helper.get_task(project_id, task_id).status != 'success':
                            break

    def run_task(self, name, project_id, key_id, git_url, inventory_id, playbook_name):
        logger.info('project_id: ' + str(project_id) + ' task name: ' + str(
            name) + ' git url: ' + git_url + ' playbook: ' + playbook_name)
        self.repository_id = self.semaphore_helper.create_repository(name, project_id, key_id, git_url)
        template_id = self.semaphore_helper.create_template(project_id, key_id, inventory_id, self.repository_id,
                                                            playbook_name)
        task_id = self.semaphore_helper.execute_task(project_id, template_id, playbook_name)
        task = self.semaphore_helper.get_task(project_id, task_id)
        last_output = ''
        while task.status == 'waiting' or task.status == 'running':
            task = self.semaphore_helper.get_task(project_id, task_id)
            logger.info('task name: ' + name + ' task status: ' + str(task.status))
            task_outputs = self.semaphore_helper.get_task_outputs(project_id, task_id)
            this_output = task_outputs[len(task_outputs) - 1].output.replace(r'\n', '\n').replace(r'\r', '\r')
            if last_output != this_output:
                logger.info('task output: ' + str(this_output))
            last_output = this_output

            # logger.info('task output: ' + str(latask name:st_output))
            sleep(3)
        return task_id
