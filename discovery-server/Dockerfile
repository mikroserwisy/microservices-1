FROM openjdk:11.0.11-9-jre-slim
ADD target/discovery-server.jar /
CMD java -jar \
    -Dspring.config.import=$CONFIGURATION_SERVER \
    discovery-server.jar