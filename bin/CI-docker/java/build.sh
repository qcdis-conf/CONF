#!/bin/bash

SRC=$1


cd $SRC

mvn -Dmaven.test.skip=true install
status=$?
[ $status -eq 0 ] && echo "build successful" || exit -1
