docker stop archive-kd:1.0.0.0
docker rm archive-kd:1.0.0.0
##docker build --force-rm --no-cache --pull -f Dockerfile -t archive-kd:1.0.0.0 .
docker run -it -d -p8083:8080 --name archive-kd1000 archive-kd:1.0.0.0
##docker cp web-ui/target/webapp/ jetty946_v20170531:/var/lib/jetty/webapps/archive-kd
docker exec -it archive-kd1000 bash
