package com.simplesys.app.http

import scala.language.implicitConversions
import scalatags.Text.all._
import scalatags.Text.tags2

class StartTestPage[Builder, Output <: FragT, FragT](val _title: String) {
    def bodyHTML(lastScript: String) = {

        html(lang := "en",
            head(
                tags2.title(_title),
                meta(httpEquiv := "Content-Type", content := "text/html; charset=utf-8"),
                script(src := "managed/javascript/jsgantt-improved/jsgantt.js"),
                script(src := "javascript/generated/generatedComponentsJS/web-uijs-fastopt.js"),
                link(href := "managed/css/jsgantt-improved/jsgantt.css", rel := "stylesheet", `type` := "text/css"),
            ),
            body(
                div(
                    id := "GanttChartDIV",
                    `class` := "gantt",
                    style := "position:relative"
                )
            ),
            script(lastScript)
        )
    }
}

