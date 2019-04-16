#!/bin/bash

SCRIPT_LOCATION=$(readlink -f "$0")
EXECUTION_PATH=$(dirname $(dirname $SCRIPT_LOCATION))


cd $EXECUTION_PATH 

echo "===== Building source code and generating jar file ====="
mvn clean package

if [ $? -ne 0 ]; then
    echo "===== ERROR BUILDING SOURCE CODE ====="
    exit 1
fi

echo "===== Renaming jar file ====="

mv "target/simple-jdbc-0.0.1-SNAPSHOT.jar" "target/app.jar"

echo "===== Building Docker image ====="
APP_NAME="remember-jdbc"
docker build -t "$APP_NAME" .

TAG="$(git rev-parse --short HEAD)-$(date +%F-%H | tr ":" "-")"

docker tag $APP_NAME "moconinja/$APP_NAME:$TAG"

echo "===== Stopping running containers (if any) and starting fresh container ====="

docker run -d --network="host" "$APP_NAME"
