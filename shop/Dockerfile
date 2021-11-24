FROM openjdk:11.0.11-9-jre-slim
ADD target/shop.jar /
CMD java -jar \
    -Dspring.config.import=$CONFIGURATION_SERVER \
    shop.jar