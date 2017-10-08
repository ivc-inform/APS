package com.simplesys.app.http

import scalatags.generic.Bundle

class StartTestPage[Builder, Output <: FragT, FragT](val _title: String, val bundle: Bundle[Builder, Output, FragT]) {
    def bodyHTML(lastScript: String) = {
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
                    link(href := "css/reset.css", rel := "stylesheet", `type` := "text/css"),
                    bundle.tags2.style(
                        body(
                            fontFamily := "tahoma, verdana, helvetica",
                            fontSize := "0.8em",
                            padding := "10px"
                        )
                    )
                ),
                br,
                div(id := "eventMessage"),
                script(src := s"javascript/developed/jquery-3.2.1.js"),
                script(src := s"javascript/developed/jquery-ui-1.12.1.js"),
                script(lastScript)
            )
        )
    }
}
