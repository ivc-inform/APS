package com.simplesys.container

import com.simplesys.common.Strings._
import com.simplesys.annotation.RSTransfer
import com.simplesys.app.http.{StartPage, StartTestPage}
import com.simplesys.common._
import com.simplesys.servlet.ServletContext
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.isc.{GetData, ServletActor}

//http://localhost:8084/aps/StartPage
@RSTransfer(urlPattern = "/StartPage")
class StartPageContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends ServletActor {

    def receive = {
        case GetData => {
            val textHTML = new StartPage("Расписание".ellipsis)

            val html: String = "<!DOCTYPE html>" +
              textHTML.bodyHTML(
                  "CreateSimpleTypes();" +
                    "CreateSmartClientJS();" +
                    "CreateAppJS();" +
                    "GetUIContent();",
                  false
              ).render.unEscape


            //logger debug html
            Out(html)

            selfStop()                                            
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    }
}

//http://localhost:8084/aps/TestDiagramPage
@RSTransfer(urlPattern = "/TestDiagramPage")
class TestDiagramPageContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends ServletActor {

    def receive = {
        case GetData => {
            val html: String = "<!DOCTYPE html>" +
              (new StartPage("Тесты".ellipsis)).bodyHTML(
                  "CreateSimpleTypes();" +
                    "CreateSmartClientJS();" +
                    "CreateAppJS();" +
                    "GetTestDiagramPageUIContent();",
                  false
              ).render.unEscape

            //val html: String = "<!DOCTYPE html>" + (new StartTestPage("Тесты".ellipsis)).bodyHTML("GetTestDiagramGantt();").render.unEscape

            //logger debug html
            Out(html)

            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    }
}

//http://localhost:8084/aps/TestDiagramImprovedPage
@RSTransfer(urlPattern = "/TestDiagramImprovedPage")
class TestDiagramImprovedPageContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends ServletActor {

    def receive = {
        case GetData => {
            val html: String = "<!DOCTYPE html>" +
              (new StartTestPage("Тесты".ellipsis)).bodyHTML(
                  "GanttImprovedTest();" //из "com.simplesys" %%% "jsgantt-improved"
              ).render.unEscape

            Out(html)
            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    }
}
