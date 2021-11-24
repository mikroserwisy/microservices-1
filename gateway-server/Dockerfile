FROM openjdk:11.0.11-9-jre-slim
ADD target/gateway-server.jar /
CMD java -jar \
    -Dspring.config.import=$CONFIGURATION_SERVER \
    gateway-server.jar