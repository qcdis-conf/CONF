#!/bin/bash

cd ../

mvn -Dmaven.test.skip=true install
status=$?
[ $status -eq 0 ] && echo "build successful" || exit -1



python_projects=(sure_tosca-flask-server sure_tosca-client_python_stubs semaphore-python-client-generated planner deployer)

for python_project in ${python_projects[*]}
do
    echo "----------------- Building:  $python_project---------------" 
    cd $python_project 
    rm -rf venv 
    python3 -m venv venv
    venv/bin/pip3 install -r requirements.txt
    if [ "$python_project" == "deployer" ]; then
        cd ../semaphore-python-client-generated
        ../$python_project/venv/bin/python setup.py install
        cd ../sure_tosca-client_python_stubs/
        ../$python_project/venv/bin/python setup.py install
    fi
    status=$?
    [ $status -eq 0 ] && echo "----------------Build: $python_project successful ------"  || exit -1
    cd ../
done

