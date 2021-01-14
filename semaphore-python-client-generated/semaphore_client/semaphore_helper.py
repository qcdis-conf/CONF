import os
import sys
from datetime import datetime

import urllib3

from semaphore_client import Configuration, ApiClient, api, ProjectRequest, Login, Repository, Inventory, \
    AccessKeyRequest, InventoryRequest, RepositoryRequest, TemplateRequest, Task


class SemaphoreHelper:

    def __init__(self, semaphore_base_url, username, password):
        self.init_semaphore_client(semaphore_base_url, username, password)


    def init_semaphore_client(self, polemarch_base_url, username, password):
        configuration = Configuration()
        configuration.host = polemarch_base_url
        configuration.username = username
        configuration.password = password
        api_client = ApiClient(configuration=configuration)
        if not api_client.cookie:
            self.authentication_api = api.AuthenticationApi(api_client=api_client)
            login_body = Login(auth=username, password=password)
            self.authentication_api.auth_login_post(login_body)
            tokens = self.authentication_api.user_tokens_get()

        self.default_api = api.DefaultApi(api_client=api_client)
        self.project_api = api.ProjectApi(api_client=api_client)
        self.user_api = api.UserApi (api_client=api_client)
        self.projects_api = api.ProjectsApi(api_client=api_client)


    def find_projects_by_name(self, name):
        projects = self.projects_api.projects_get()
        projects_with_name = []
        for project in projects:
            if project.name == name:
                projects_with_name.append(project)
        return projects_with_name

    def project_with_name_exists(self, name):
        projects = self.find_projects_by_name(name)
        if not projects:
            return False
        return True

    def create_project(self, name):
        project_request = ProjectRequest(name=name)
        self.projects_api.projects_post(project_request)
        projects = self.find_projects_by_name(name)
        return self.find_latest_project(projects).id

    def create_ssh_key(self, name, project_id, private_key):
        key_request = AccessKeyRequest(name=name, type='ssh', project_id=project_id, secret=private_key)
        self.project_api.project_project_id_keys_post(key_request, project_id )
        keys = self.project_api.project_project_id_keys_get(project_id, name, 'asc', key_type='ssh')
        return keys[0].id

    def create_inventory(self, name, project_id,ssh_key_id, inventory_contents):
        inventory_request = InventoryRequest( name=name, project_id=project_id, inventory=inventory_contents,
                                              ssh_key_id=ssh_key_id, type='static')
        self.project_api.project_project_id_inventory_post(inventory_request,project_id)

        inventories = self.project_api.project_project_id_inventory_get(project_id, name, 'asc')
        return inventories[0].id

    def create_repository(self, name, project_id, key_id, git_url):
        repository_request = RepositoryRequest(name=name, project_id=project_id, git_url=git_url, ssh_key_id=key_id)
        self.project_api.project_project_id_repositories_post(repository_request ,project_id)
        repository = self.find_repository(project_id, name, git_url)
        return repository.id

    def create_template(self, project_id,key_id,inventory_id,repository_id,playbook_name):
        template_request = TemplateRequest(ssh_key_id=key_id, project_id=project_id, inventory_id=inventory_id,
                                           repository_id=repository_id, alias=playbook_name, playbook=playbook_name)
        self.project_api.project_project_id_templates_post(template_request , project_id )
        templates = self.project_api.project_project_id_templates_get(project_id, playbook_name, 'asc')
        return self.find_template(templates,playbook_name).id

    def execute_task(self, project_id, template_id, playbook_name):
        task = Task(template_id=template_id, playbook=playbook_name)
        self.project_api.project_project_id_tasks_post(task,project_id)
        tasks = self.project_api.project_project_id_tasks_get(project_id)
        return self.find_latest_task(tasks).id

    def get_task(self,project_id,task_id):
        return self.project_api.project_project_id_tasks_task_id_get(project_id,task_id)


    @classmethod
    def service_is_up(cls, url):
        code = None
        try:
            http = urllib3.PoolManager()
            r = http.request('HEAD', url)
        except Exception as e:
            return False

        return True

    def get_task_outputs(self, project_id, task_id):
        return self.project_api.project_project_id_tasks_task_id_output_get(project_id,task_id)

    def find_latest_project(self, projects):
        now = datetime.now()
        earlier = datetime.strptime('1900-01-01T10:07:35Z',  '%Y-%m-%dT%H:%M:%SZ')
        min_time_delta = now - earlier
        latest_project = None
        for project in projects:
            date_object = datetime.strptime(project.created,  '%Y-%m-%dT%H:%M:%SZ')
            time_delta = now - date_object
            if time_delta < min_time_delta:
                latest_project = project
        return latest_project

    def find_latest_task(self, tasks):
        return tasks[0]

    def find_template(self, templates,playbook_name):
        for template in templates:
            if template.playbook == playbook_name:
                return template

    def find_repository(self, project_id, name, git_url):
        repositories = self.project_api.project_project_id_repositories_get(project_id, name, 'asc')
        for repo in repositories:
            if repo.git_url == git_url:
                return repo
        return None