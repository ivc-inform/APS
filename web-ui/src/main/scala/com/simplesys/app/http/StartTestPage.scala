package com.simplesys.app.http

import scalatags.Text.all._
import scalatags.Text.tags2
import scalatags.stylesheet.StyleSheet

class StartTestPage[Builder, Output <: FragT, FragT](val _title: String){
    def bodyHTML(lastScript: String) = {

        html(lang := "en",
            head(
                tags2.title(_title),
                meta(httpEquiv := "Content-Type", content := "text/html; charset=utf-8")
            ),
            body(
                div(
                    id := "ganttChart",
                    link(href := "css/jquery-ui-1.12.1.css", rel := "stylesheet", `type` := "text/css"),
                    link(href := "css/reset.css", rel := "stylesheet", `type` := "text/css"),
                    link(href := "css/jquery.ganttView.css", rel := "stylesheet", `type` := "text/css"),
                    tags2.style("body {font-family: tahoma, verdana, helvetica; font-size: 0.8em; padding: 10px;}")
                ),
                br,
                div(id := "eventMessage"),
                script(src := s"javascript/developed/jquery-3.2.1.js"),
                script(src := s"javascript/developed/jquery-ui-1.12.1.js"),
                script(src := s"javascript/generated/generatedComponentsJS/web-ui-fastopt.js"),
                script(lastScript)
            )
        )
    }
}
