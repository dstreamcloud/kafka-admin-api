#!/bin/bash -e

export VERSION=$(xq .project.version pom.xml | tr -d '"')
echo "Building version: ${VERSION}"
mkdir -p src/main/resources
cat << EOF |> src/main/resources/application.properties
server.port = 8080
kafka.servers: localhost:9092
EOF
mvn clean package spring-boot:repackage
cp target/kafka-admin-api-${VERSION}.jar /app.jar