package com.simplesys.app.http

import scalatags.generic.Bundle

case class paths(subPath: String, subPath1: String)

class StartPage[Builder, Output <: FragT, FragT](val _title: String, val bundle: Bundle[Builder, Output, FragT]) {
    def bodyHTML(lastScript: String, fullOpt: Boolean = false) = {
        import bundle.all._

        html(lang := "en",
            head(
                bundle.tags2.title(_title),
                meta(httpEquiv := "Content-Type", content := "text/html; charset=utf-8")
            ),
            body(
                div(
                    id := "ganttChart",
                    link(href := "css/jquery.ganttView.css", rel := "stylesheet", `type` := "text/css"),
                    link(href := "css/jquery-ui-1.12.1.css", rel := "stylesheet", `type` := "text/css"),
                    link(href := "css/jquery-ui-1.12.1.css", rel := "stylesheet", `type` := "text/css")
                ),

                script(lastScript)
            )
        )
    }
}
