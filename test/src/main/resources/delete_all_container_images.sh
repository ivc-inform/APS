#!/bin/bash
# Delete all containers
docker stop $(docker ps -q)

docker rm $(docker ps -a -q)
# Delete all images
docker rmi $(docker images -q)

#docker volume rm $(docker volume ls)
docker volume prune
