from service import tosca_helper, ansible_service
from service.ansible_service import AnsibleService


class DeployService:

    def __init__(self, semaphore_base_url=None, semaphore_username=None, semaphore_password=None):
        self.semaphore_base_url = semaphore_base_url
        self.semaphore_username = semaphore_username
        self.semaphore_password = semaphore_password

    def deploy(self, nodes_pair):
        target = nodes_pair[0]
        source = nodes_pair[1]

        interface_types = tosca_helper.get_interface_types(source)
        if interface_types:
            if 'Standard' in interface_types:
                input_ansible_service = AnsibleService(self.semaphore_base_url, self.semaphore_username,
                                                       self.semaphore_password)
                input_ansible_service.execute(nodes_pair)
            else:
                print(interface_types)

        return None
