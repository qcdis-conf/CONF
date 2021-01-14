#!/bin/bash

FOLDER_NAME=$1
SONAR_HOST=$2
SONAR_LOGIN=$3
SONAR_PROPERTIES_FILE=$4

/opt/sonar-scanner/bin/sonar-scanner -X -e -D sonar.host.url=$SONAR_HOST -D sonar.projectBaseDir=$FOLDER_NAME -D project.settings=$SONAR_PROPERTIES_FILE -D sonar.login=$SONAR_LOGIN
