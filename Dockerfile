FROM maven:3-openjdk-8 AS build

RUN apt-get update -y
RUN apt-get install python3 python3-pip jq -y
RUN pip3 install yq

WORKDIR /workspace
COPY . /workspace/
RUN /workspace/build.sh

FROM gcr.io/distroless/java-debian10
COPY --from=build /app.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
