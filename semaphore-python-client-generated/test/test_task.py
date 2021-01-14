# coding: utf-8

"""
    SEMAPHORE

    Semaphore API  # noqa: E501

    OpenAPI spec version: 2.2.0

    Generated by: https://github.com/semaphore-api/semaphore-codegen.git
"""


from __future__ import absolute_import

import unittest
from time import sleep

import  yaml
import semaphore_client
from semaphore_client.models.task import Task  # noqa: E501
from semaphore_client.rest import ApiException
from semaphore_client.semaphore_helper import SemaphoreHelper
import names

yaml.Dumper.ignore_aliases = lambda *args : True

class TestTask(unittest.TestCase):
    """Task unit test stubs"""

    def setUp(self):
        self.semaphore_base_url = 'http://localhost:3000/api'
        if SemaphoreHelper.service_is_up(self.semaphore_base_url):
            self.username = 'admin'
            self.password = 'password'
            self.project_name = 'test'
            self.private_key = '-----BEGIN RSA PRIVATE KEY-----MIIEowIBAAKCAQEAg0blRNV6cm3RTiivpzE8HR4JzKZRVIBZ7bxeNoMz0' \
                               '-----END RSA PRIVATE KEY-----'
            vms = []
            vm = {}
            vm['role'] = 'master'
            vm['public_ip'] = '192.168.1.10'
            vm['user_name'] = 'user_name'
            vms.append(vm)
            vm['role'] = 'worker'
            vm['public_ip'] = '192.168.1.11'
            vm['role'] = 'worker'
            vm['public_ip'] = '192.168.1.12'
            vms.append(vm)
            self.inventory_contents =  yaml.dump( self.build_yml_inventory(vms),default_flow_style=False)
            self.git_url = 'https://github.com/skoulouzis/playbooks.git'
            self.playbook_name = 'get_ip_addresses.yaml'
            self.semaphore_helper = SemaphoreHelper(self.semaphore_base_url, self.username, self.password)

    def tearDown(self):
        pass

    def testTask(self):
        if SemaphoreHelper.service_is_up(self.semaphore_base_url):
            project_id = self.semaphore_helper.create_project(self.project_name)
            key_id = self.semaphore_helper.create_ssh_key(self.project_name, project_id, self.private_key)
            inventory_id = self.semaphore_helper.create_inventory(self.project_name, project_id, key_id, self.inventory_contents)

            repository_id = self.semaphore_helper.create_repository(self.project_name, project_id, key_id, self.git_url)
            template_id = self.semaphore_helper.create_template(project_id, key_id, inventory_id, repository_id, self.playbook_name)
            task_id = self.semaphore_helper.execute_task(project_id, template_id, self.playbook_name)
            for x in range(0, 2):
                task = self.semaphore_helper.get_task(project_id, task_id)
                print(task)
                # task_output = self.semaphore_helper.get_task_output(project_id, task_id)
                # print(task_output)
                sleep(1)

            task_id = self.semaphore_helper.execute_task(project_id, template_id, self.playbook_name)
            for x in range(0, 2):
                task = self.semaphore_helper.get_task(project_id, task_id)
                print(task)
                # task_output = self.semaphore_helper.get_task_output(project_id, task_id)
                # print(task_output)
                sleep(1)
            task_id = self.semaphore_helper.execute_task(project_id, template_id, 'mount.yaml')
            for x in range(0, 2):
                task = self.semaphore_helper.get_task(project_id, task_id)
                print(task)
                # task_output = self.semaphore_helper.get_task_output(project_id, task_id)
                # print(task_output)
                sleep(1)



    def build_yml_inventory(self, vms):
        inventory = {}
        all = {}
        vars = {'ansible_ssh_common_args':'-o StrictHostKeyChecking=no'}
        vars['ansible_ssh_user'] = vms[0]['user_name']
        children = {}
        for vm in vms:
            role = vm['role']
            public_ip = vm['public_ip']

            if role not in children:
                hosts = {}
            else:
                hosts = children[role]
            hosts[public_ip] = vars
            children[role] = hosts
        all['children'] = children
        inventory['all'] = all
        return inventory


if __name__ == '__main__':
    unittest.main()
