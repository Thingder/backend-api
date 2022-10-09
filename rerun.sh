docker network create thingder_network;

./gradlew build -x test;
docker build -t thingder_api .;

docker stop thingder_api_1;
docker rm thingder_api_1;

docker run \
    --network thingder_network \
    -p 8080:8080 \
    --name thingder_api_1 \
    -d \
    thingder_api ;

docker system prune --volumes -f -a;