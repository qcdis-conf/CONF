#! /usr/bin/env python

# Copyright 2017 --Yang Hu--
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.


__author__ = 'Yang Hu'

import paramiko, os
from vm_info import VmInfo
import logging

# create logger
logger = logging.getLogger('docker_swarm')
logger.setLevel(logging.DEBUG)
# create console handler and set level to debug
ch = logging.StreamHandler()
ch.setLevel(logging.DEBUG)
# create formatter
formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
# add formatter to ch
ch.setFormatter(formatter)
# add ch to logger
logger.addHandler(ch)


def deploy_compose(vm, compose_file, compose_name):
    try:
        logger.info("Starting docker compose deployment on: "+vm.ip)        
        paramiko.util.log_to_file("deployment.log")
        ssh = paramiko.SSHClient()
        ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
        ssh.connect(vm.ip, username=vm.user, key_filename=vm.key)
        sftp = ssh.open_sftp()
        sftp.chdir('/tmp/')
        sftp.put(compose_file, "docker-compose.yml")
        stdin, stdout, stderr = ssh.exec_command("sudo docker stack deploy --compose-file /tmp/docker-compose.yml %s" % (compose_name))
        stdout.read()
        logger.info("Finished docker compose deployment on: "+vm.ip)        
    except Exception as e:
        logger.error(vm.ip + " " + str(e))
        return "ERROR:" + vm.ip + " " + str(e)
    ssh.close()
    return "SUCCESS"




def run(vm_list, compose_file, compose_name):
    for i in vm_list:
        if i.role == "master":
            ret = deploy_compose(i, compose_file, compose_name)
            if "ERROR" in ret:
                return ret
            else:
                swarm_file = open(i.key)
                ret = swarm_file.read()
                swarm_file.close()
            break


    return ret