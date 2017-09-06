package com.ivcInform.http.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.ivcInform.app.http.StartPage
import com.simplesys.config.Config
import com.simplesys.log.Logging
import com.simplesys.common._
import com.ivcInform.common._

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
        path("Hello") {
            get {
                complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
            }
        } ~
          path("StartPage") {
              get {
                  val textHTML = new StartPage("ПРОБА !!!!!".ellipsis, scalatags.Text)
                  val html = "<!DOCTYPE html>" +
                    textHTML.bodyHTML(
                        "GetUIContent();",
                        false
                    ).render.unEscape

                  complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, html))
              }
          }

    val bindingFuture = Http().bindAndHandle(route, host, port)

    sys.addShutdownHook {
        shutdownIt(bindingFuture, system)
    }

    logger.info(s"Server online at http://${host}:${port}/StarPage")
}
