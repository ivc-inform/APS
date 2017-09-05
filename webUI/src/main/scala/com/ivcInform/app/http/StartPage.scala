package com.ivcInform.app.http

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
                style := "margin: 0px",

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

                script(src := s"javascript/generated/generatedComponents/MakeAboutData.js"),

                script(src := s"javascript/generated/generatedComponentsJS/${if (fullOpt) "web-ui-opt.js" else "web-ui-fastopt.js"}"),

                link(href := "managed/css/common-webapp/logging_styles.css", rel := "stylesheet", `type` := "text/css"),
                script(lastScript)
            )
        )
    }
}
