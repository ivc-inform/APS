package com.ivcInform.app.http

import scalatags.generic.Bundle


class StartPage[Builder, Output <: FragT, FragT](val _title: String, val webappPath: String, val bundle: Bundle[Builder, Output, FragT]) {
    def bodyHTML(lastScript: String, fullOpt: Boolean = false) = {
        import bundle.all._

        html(lang := "en",
            head(
                bundle.tags2.title(_title),
                meta(httpEquiv := "Content-Type", content := "text/html; charset=utf-8")
            ),
            body(
                style := "margin: 0px",

                script("var isomorphicDir =\"" + webappPath + "isomorphic/\""),
                script(src := s"${webappPath}isomorphic/client/modules/ISC_Core.js"),
                script(src := s"${webappPath}isomorphic/client/modules/ISC_Foundation.js"),
                script(src := s"${webappPath}isomorphic/client/modules/ISC_Containers.js"),
                script(src := s"${webappPath}isomorphic/client/modules/ISC_Grids.js"),
                script(src := s"${webappPath}isomorphic/client/modules/ISC_Forms.js"),
                script("isc.licenseType = \"Enterprise\""),
                script(src := s"${webappPath}isomorphic/client/modules/ISC_DataBinding.js"),
                script(src := s"${webappPath}isomorphic/client/modules/ISC_Calendar.js"),
                script(src := s"${webappPath}isomorphic/client/modules/ISC_RichTextEditor.js"),
                script(src := s"${webappPath}isomorphic/client/modules/ISC_History.js"),
                script(src := s"${webappPath}isomorphic/client/modules/ISC_Workflow.js"),
                script(src := s"${webappPath}isomorphic/client/modules/ISC_Drawing.js"),
                script(src := s"${webappPath}isomorphic/client/modules/ISC_Charts.js"),
                script(src := s"${webappPath}isomorphic/client/modules/ISC_FileLoader.js"),

                script(src := s"${webappPath}javascript/generated/generatedComponents/MakeAboutData.js"),

                script(src := s"${webappPath}javascript/generated/generatedComponentsJS/${if (fullOpt) "web-ui-opt.js" else "webuijs-fastopt.js"}"),

                link(href := s"${webappPath}css/logging_styles.css", rel := "stylesheet", `type` := "text/css"),
                script(lastScript)
            )
        )
    }
}
