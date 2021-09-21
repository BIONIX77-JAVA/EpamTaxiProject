echo "PWD: " "$PWD"

docker stop postgres
docker rm -f postgres

docker-compose up --build -d
