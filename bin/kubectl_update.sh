#!/bin/bash

deployments=(manager sure-tosca planner provisioner deployer)

for deployment in ${deployments[*]}
do
    echo "----------------- updating image for $deployment---------------" 
    kubectl set image deployment $deployment $deployment=qcdis/$deployment:3.0.0 -n conf
    kubectl rollout history deployment $deployment -n conf
done


