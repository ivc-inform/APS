package com.simplesys.app.http

import com.simplesys.gantt.GanttChart._
import com.simplesys.gantt._
import org.scalajs.dom

import scala.language.implicitConversions
import scala.scalajs.js.annotation.JSExportTopLevel
import scalatags.Text.all._
import scalatags.Text.tags2

class StartTestPage[Builder, Output <: FragT, FragT](val _title: String) {
    def bodyHTML(lastScript: String) = {

        html(lang := "en",
            head(
                tags2.title(_title),
                meta(httpEquiv := "Content-Type", content := "text/html; charset=utf-8"),
                script("var isomorphicDir = \"isomorphic/\""),
                script(src := "isomorphic/client/modules/ISC_Core.js"),
                script(src := "isomorphic/client/modules/ISC_Foundation.js"),
                script(src := "isomorphic/client/modules/ISC_Containers.js"),
                script(src := "isomorphic/client/modules/ISC_Grids.js"),
                script(src := "isomorphic/client/modules/ISC_Forms.js"),
                script("isc.licenseType = \"Enterprise\""),
                script(src := "isomorphic/client/modules/ISC_DataBinding.js"),
                script(src := "isomorphic/client/modules/ISC_Calendar.js"),
                script(src := "isomorphic/client/modules/ISC_RichTextEditor.js"),
                script(src := "isomorphic/client/modules/ISC_History.js"),
                script(src := "isomorphic/client/modules/ISC_Workflow.js"),
                script(src := "isomorphic/client/modules/ISC_Drawing.js"),
                script(src := "isomorphic/client/modules/ISC_Charts.js"),
                script(src := "isomorphic/client/modules/ISC_FileLoader.js"),
                script(src := "managed/javascript/jsgantt-improved/jsgantt.js"),
                script(src := "javascript/generated/generatedComponentsJS/web-ui-fastopt.js"),
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

