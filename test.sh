#!/bin/bash

mvn test

cd drip-planner && python3 -m unittest test/test_planner.py