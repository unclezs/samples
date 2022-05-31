#!/usr/bin/env bash

cd ..

mvn clean package -DskipTests

cp target/samples-java-*.jar oom/oom.jar

cd - || exit