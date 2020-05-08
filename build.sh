#!/bin/bash -e

export VERSION=$(xq .project.version pom.xml | tr -d '"')
echo "Building version: ${VERSION}"
cat << EOF |> src/main/resources/application.yaml
server.port: 8080
kafka.servers: []
EOF
mvn clean package spring-boot:repackage
cp target/kafka-admin-api-${VERSION}.jar /app.jar