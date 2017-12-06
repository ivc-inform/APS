docker stop aps:1.0.0.0
docker rm aps:1.0.0.0
##docker build --force-rm --no-cache --pull -f Dockerfile -t aps:1.0.0.0 .
docker run -it -d -p8083:8080 --name aps1000 aps:1.0.0.0
##docker cp web-ui/target/webapp/ jetty946_v20170531:/var/lib/jetty/webapps/aps
docker exec -it aps1000 bash
