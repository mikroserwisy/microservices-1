FROM openjdk:11.0.11-9-jre-slim
ADD target/products-server.jar /
CMD java -jar \
    -Dspring.config.import=$CONFIGURATION_SERVER \
    products-server.jar