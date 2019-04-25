#!/usr/bin/env bash

echo "Starting file validation and SCP to Tomcat Server..."

PEM_FILE=secrets/betty-webserver-key.pem
DEPLOY_FOLDER=/opt/tomcat/latest/webapps/
WAR_FILE=build/libs/betty-0.0.1-SNAPSHOT.war

if [ ! -f "$WAR_FILE" ]; then
    echo "$WAR_FILE does not exist"
    exit 127
fi

if [ ! -f "$PEM_FILE" ]; then
    echo "$PEM_FILE does not exist"
    exit 127
fi

chmod 400 $PEM_FILE
scp -o StrictHostKeyChecking=no -i $PEM_FILE $WAR_FILE ubuntu@3.19.108.19:"$DEPLOY_FOLDER"ROOT.war
echo "PEACE TO WORLD"


