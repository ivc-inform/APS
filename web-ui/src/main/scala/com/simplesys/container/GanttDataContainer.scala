package com.simplesys.container

import com.simplesys.app.http.StartPage
import com.simplesys.servlet.ServletContext
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.isc.{GetData, ServletActor}

class GanttDataContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends ServletActor {

    def receive = {
        case GetData => {

            Out()

            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    }
}
