./mvnw clean package -DskipTests
docker build -t configuration-server configuration-server
docker build -t discovery-server discovery-server
docker build -t gateway-server gateway-server
docker build -t products products
docker build -t shop shop