#!/usr/bin/env bash

export CLASSPATH=./target/P2Y-jar-withdependencies.jar
java -cp $CLASSPATH pvi.utils.P2Y $*