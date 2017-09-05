package com.ivcInform.http.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.simplesys.log.Logging
import scala.concurrent.duration._

import scala.concurrent.{Await, Future}

object WebServer extends App with Logging {
    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()

    def shutdownIt(bindingFuture: Future[Http.ServerBinding], system: ActorSystem): Unit = {

        implicit val executionContext = system.dispatcher
        logger.info(s"shutting down actor system ${system.name} and stopping http server")

        bindingFuture
          .flatMap(_.unbind()) // trigger unbinding from the port
          .onComplete(_ => system.terminate()) // and shutdown when done

        Await.result(system.whenTerminated, 3 minute)
    }

    implicit val executionContext = system.dispatcher

    val route =
        path("hello") {
            get {
                complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
            }
        }

    val bindingFuture = Http().bindAndHandle(route, AppSettings.http.host, AppSettings.http.port)

    logger.info(s"Server online at http://${AppSettings.http.host}:${AppSettings.http.port}/\nPress Ctrl-C to stop...")

    sys.addShutdownHook {
        shutdownIt(bindingFuture, system)
    }

}
