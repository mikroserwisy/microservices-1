FROM openjdk:11.0.11-9-jre-slim
ADD target/configuration-server.jar /
CMD java -jar \
    -Dspring.cloud.config.server.git.uri=$GIT_URI \
    configuration-server.jar