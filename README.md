# About 

CONF  allows application developers to seamlessly plan a customized virtual infrastructure based on application 
level constraints on QoS and resource budgets, provisioning the virtual infrastructure using standardized interfaces 
(e.g., TOSCA and OCCI), deploy application components onto the virtual infrastructure, and start execution on demand.
 
CONF is developed by the research team for QCDIS team (quality critical distributed computing), in the 
[MNS group [(Multi scale networking),(https://ivi.fnwi.uva.nl/sne/research/mns/)]
of the University of Amsterdam. The development of CONF is coordinated by Dr. Zhiming Zhao. Core developers 
include Junchao Wang, Huan Zhou, Yang Hu, Paul Martin, Arie Taal and Spiros Koulouzis.
 


# CONF
This is the integration of the CONF services as docker conatiners. All components of this architecture are build as docker containers. 

## Install 

### Docker Stack

To run the engine using docker stack use [docker-compose.yaml](https://github.com/qcdis-conf/CONF/blob/master/docker-compose/docker-compose.yml)
```
sudo docker stack deploy conf -c docker-compose.yml
```

### Kubernetes 

To run the engine on a kubernetes cluster use the resouces at the [k8s folder]( https://github.com/qcdis-conf/CONF/tree/master/k8s/CONF ).
To deploy clone this project on your K8s master node or configure your kubectl context accordingly. Next go to the k8s/CONF and run the following:
Create the namespace 
```
kubectl create -f namespace/conf-namespace.yaml
```

Create the databases
```
kubectl apply -f namespace/conf-namespace.yaml
```

Create the CONF services 
```
kubectl apply -f .
```

# CONF API usage 

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





