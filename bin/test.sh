#!/bin/bash


docker stack deploy conf-test -c ../docker-compose-test.yml

sure_tosca_url=http://localhost:8081

count=0 && until $(curl --output /dev/null --silent --head --fail "$sure_tosca_url"); do printf '.' && sleep 10 && count=$((count+1)) && if [ $count -gt 5 ]; then break; fi; done  


if curl --output /dev/null --silent --head --fail "$sure_tosca_url"; then
  printf '%s\n' "$sure_tosca_url exist"
else
  printf '%s\n' "$sure_tosca_url does not exist"
fi


cd ../
mvn test
status=$?
[ $status -eq 0 ] && echo "------- Java tests successful------" || exit -1

cd planner && venv/bin/python3 -m unittest discover
status=$?
[ $status -eq 0 ] && echo "------- Planner tests successful------" || exit -1

cd ../
cd sure_tosca-flask-server && venv/bin/python3 -m unittest discover
status=$?
[ $status -eq 0 ] && echo "------- sure_tosca-flask-server tests successful------" || exit -1

cd ../
cd sure_tosca-client_python_stubs  && venv/bin/python3 -m unittest discover
status=$?
[ $status -eq 0 ] && echo "------- sure_tosca-client_python_stubs tests successful------" || exit -1


cd ../
cd semaphore-python-client-generated  && venv/bin/python3 -m unittest discover
status=$?
[ $status -eq 0 ] && echo "------- semaphore-python-client-generated tests successful------" || exit -1


cd ../
cd deployer  && venv/bin/python3 -m unittest discover
status=$?
[ $status -eq 0 ] && echo "------- deployer tests successful------" || exit -1

docker stack rm conf-test
