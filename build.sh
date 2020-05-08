#!/bin/bash -e

export VERSION=$(xq .project.version pom.xml | tr -d '"')
echo "Building version: ${VERSION}"
mvn clean package spring-boot:repackage
cp target/kafka-admin-api-${VERSION}.jar /app.jar