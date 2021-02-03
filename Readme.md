## Install


All components of this architecture are build as docker containers. 
To quickly run the engine use this [docker-compose.yaml](./blob/develop/docker-compose.yml)
```
sudo docker stack deploy qcdie -c docker-compose.yml
```

### Services
The architecture contains the following services:
* Rabbitmq: It is used to pass messages between the manager and the planner, provisioner and deployer 
* MySQL: Used by Ansible Semaphore
* Ansible Semaphore: This is used by the deployer to run playbooks on provisioned VMs to mainly install k8s cluster 
* Logspout: A helping service to display all the log messages of all running services 
* MongoDB: Used by the manager to store TOSCA templates 
* TOSCA Simple qUeRy sErvice (SURE): A RESTful service for verifying and querying TOSCA templates. The API definition and documentation  can be found here https://app.swaggerhub.com/apis-docs/skoulouzis/tosca-sure/1.0.0
* Planner: This service takes a TOSCA template as input that may contain only an application definition and generates a valid plan i.e. a plan that has all dependencies resolved.
* Provisioner: This service contacts a cloud provider to set up the virtual infatuate (mainly VMs)
* Deployer: This service initially deploys installs and  configures k8's cluster on the virtual infatuate and next install docker containers on that cluster 


## Quck Start with bash

### Provision 
You can check the tosca template that will be used: 
```
curl -k -u $AUTH -X GET "$CONF_HOST/orchestrator/tosca_template/$TOSCA_ID" | yq eval
```

Provision the topology (this will take some time):
```
PROVISION_ID=`curl -k -u $AUTH -X GET "$CONF_HOST/orchestrator/provisioner/provision/$TOSCA_ID"` 
```
You can check the update tosca template that contains some attributes such as the public IP etc.: 
```
curl -k -u $AUTH -X GET "$CONF_HOST/orchestrator/tosca_template/$PROVISION_ID" | yq eval
```

### Log in to the VMs

Download the tosca template:
```
curl -k -u $AUTH -X GET "$CONF_HOST/orchestrator/tosca_template/$PROVISION_ID" | yq eval > provisioned.yaml
```

save the private ssh key:
```
yq eval .topology_template.node_templates.compute.attributes.user_key_pair.keys.private_key provisioned.yaml | base64 -d > vm_key 
```

change permissions:
```
chmod 600 vm_key
```

Get the user name and public IP:
```
VM_USER=`yq eval .topology_template.node_templates.compute.properties.user_name provisioned.yaml`
IP=`yq eval .topology_template.node_templates.compute.attributes.public_ip provisioned.yaml`
```

Now you can login to the VM:
```
ssh $VM_USER@$IP -i vm_key
```

### Deploy k8s & Get k8s configuration

Send request to deploy (this will take several minutes):
```
DEPLOY_ID=`curl -k -u $AUTH -X GET "$CONF_HOST/orchestrator/deployer/deploy/$PROVISION_ID"`
```

Download the  tosca template:
```
curl -k -u $AUTH -X GET "$CONF_HOST/orchestrator/tosca_template/$DEPLOY_ID" > deployment.yaml
```
Get the k8s dashboard url:
```
yq eval .topology_template.node_templates.kubernetes.attributes.dashboard_url  deployment.yaml
```
Get the k8s dashboard token:
```
yq eval .topology_template.node_templates.kubernetes.attributes.tokens  deployment.yaml
```
Create the kube dir:
```
mkdir ~/.kube/
```

Get the confguration file for kubectl and save it to  ~/.kube/config:
```
yq eval .topology_template.node_templates.kubernetes.attributes.config deployment.yaml > ~/.kube/config
```
In  ~/.kube/config in the part of the k8s API url you may need to replace the IP address with the master's public IP. To get the master's public IP type:
```
yq eval .topology_template.node_templates.compute.attributes.public_ip deployment.yaml
```

Now you can test the k8s cluster:
```
kubctl get all 
```
