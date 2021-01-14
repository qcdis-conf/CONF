from __future__ import absolute_import

import unittest

import semaphore_client
from semaphore_client import APIToken, RepositoryRequest
from semaphore_client.models.login import Login  # noqa: E501
from semaphore_client.rest import ApiException

from semaphore_client.semaphore_helper import SemaphoreHelper


class TestGetRepository(unittest.TestCase):
    """Login unit test stubs"""

    def setUp(self):
        self.semaphore_base_url = 'http://localhost:3000/api'
        if SemaphoreHelper.service_is_up(self.semaphore_base_url):
            self.username = 'admin'
            self.password = 'password'

    def tearDown(self):
        pass

    def testGetRepository(self):
        """Test Login"""
        if SemaphoreHelper.service_is_up(self.semaphore_base_url):
            helper = SemaphoreHelper(self.semaphore_base_url, self.username, self.password)
            project_name = 'test'
            key = '-----BEGIN RSA PRIVATE KEY-----MIIEowIBAAKCAQEAg0blRNV6cm3RTiivpzE8HR4JzKZRVIBZ7bxeNoMz0' \
                               '-----END RSA PRIVATE KEY-----'
            project_id = helper.create_project(project_name)
            git_url_1 = 'https://github.com/QCDIS/playbooks.git'
            key_id = helper.create_ssh_key(project_name, project_id, key)

            repository_id_1 = helper.create_repository(project_name, project_id, key_id,git_url_1)

            git_url_2 = 'https://github.com/QCDIS/mysome_glusterfs.git'
            repository_id_2 = helper.create_repository(project_name, project_id,key_id, git_url_2)
            self.assertNotEqual(repository_id_1,repository_id_2)



if __name__ == '__main__':
    unittest.main()
