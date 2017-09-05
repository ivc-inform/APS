package com.ivcInform.http.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.simplesys.config.Config
import com.simplesys.log.Logging

import scala.concurrent.Future

object WebServer extends App with Config with Logging {
    implicit val system = ActorSystem(config.getString("aps.name"))
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val host = config.getString("aps.http.host")
    val port = config.getInt("aps.http.port")

    def shutdownIt(bindingFuture: Future[Http.ServerBinding], system: ActorSystem): Unit = {

        logger.info(s"shutting down actor system ${system.name} and stopping http server")
        
        bindingFuture
          .flatMap(_.unbind()) // trigger unbinding from the port
          .onComplete(_ => system.terminate()) // and shutdown when done

        system.terminate()
    }


    val route =
        path("hello") {
            get {
                //val textHTML = new StartPage("ПРОБА !!!!!".ellipsis, scalatags.Text)
                
                complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h2>Say hello to akka-http</h2>"))
            }
        }

    val bindingFuture = Http().bindAndHandle(route, host, port)

    logger.info(s"Server online at http://${host}:${port}")

    sys.addShutdownHook {
        shutdownIt(bindingFuture, system)
    }
}
