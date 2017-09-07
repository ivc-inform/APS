package com.ivcInform.http.server

import java.io.File

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{RequestContext, Route}
import akka.stream.ActorMaterializer
import com.ivcInform.app.http.StartPage
import com.simplesys.config.Config
import com.simplesys.log.Logging
import com.simplesys.common._
import com.ivcInform.common._

import scala.concurrent.Future
import com.simplesys.io._

object WebServer extends App with Config with Logging {

    implicit val system = ActorSystem(config.getString("aps.name"))
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val host = config.getString("aps.http.host")
    val port = config.getInt("aps.http.port")

    val webAppDirectory = System.getProperty("user.dir").asPath.parent.get / "webapp"
    logger debug s"workingDirectory: $webAppDirectory exists: ${webAppDirectory}"

    def shutdownIt(bindingFuture: Future[Http.ServerBinding], system: ActorSystem): Unit = {

        logger.info(s"shutting down actor system ${system.name} and stopping http server")

        bindingFuture
          .flatMap(_.unbind()) // trigger unbinding from the port
          .onComplete(_ => system.terminate()) // and shutdown when done

        system.terminate()
    }

    val route =
        /*(get & pathPrefix("webapp")) {
            extractUnmatchedPath { remaining =>
                val filePath = webAppDirectory.toFile.getAbsolutePath + remaining.toString()
                val file = new File(filePath)

                if (file.exists) {
                    logger debug  s"filePath: $filePath exist: ${file.exists()}"
                    getFromFile(file)
                }
                else {
                    logger error s"filePath: $filePath exist: ${file.exists()}"
                    reject
                }
            }
        } ~*/
          (get & path("Hello")) {
              complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say Hello APS !!!! ))</h1>"))
          } ~
          (get & path("StartPage")) {
              /*val textHTML = new StartPage("ПРОБА !!!!!".ellipsis, "../webapp/", scalatags.Text)
              val html = "<!DOCTYPE html>" +
                textHTML.bodyHTML(
                    "GetUIContent();",
                    false
                ).render.unEscape

              complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, html))*/
              complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>StartPage</h1>"))
          }

    val bindingFuture = Http().bindAndHandle(route, host, port)

    sys.addShutdownHook {
        shutdownIt(bindingFuture, system)
    }

    logger.info(s"Server online at http://${host}:${port}/StarPage")
    logger.info(s"Server online for test Hello, at http://${host}:${port}/Hello")
}
