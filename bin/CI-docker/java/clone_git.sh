#!/bin/bash


BRANCH=$1
GIT_URL=$2
FOLDER_NAME=$3

git clone -b $BRANCH $GIT_URL $FOLDER_NAME
