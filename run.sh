echo "Packaing application..."
./mvnw clean package
cp ./target/products-orders-0.0.1-SNAPSHOT.jar docker/app.jar
pushd docker
docker-compose up -d