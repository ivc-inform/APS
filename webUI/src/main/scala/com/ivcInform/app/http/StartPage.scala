package com.ivcInform.app.http

import scalatags.generic.Bundle


class StartPage[Builder, Output <: FragT, FragT](val _title: String, val webappPath: String, val bundle: Bundle[Builder, Output, FragT]) {
    def bodyHTML(lastScript: String, fullOpt: Boolean = false) = {
        import bundle.all._

        val webappPath1 = webappPath + "managed/isc-components/generated/generatedComponents/coffeescript/coffeeScript/main/com/simplesys/"
        
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
                script(src := s"${webappPath}managed/javascript/common-webapp/generated/generatedComponents/coffeescript/coffeeScript/main/common.js"),

                script(src := s"${webappPath1}PreDefined.js"),
                script(src := s"${webappPath1}system/ArraySS.js"),
                script(src := s"${webappPath1}system/LogSS.js"),
                script(src := s"${webappPath1}RPCManagerSS.js"),
                script(src := s"${webappPath1}DataSourceSS.js"),
                script(src := s"${webappPath1}commons.js"),
                script(src := s"${webappPath1}OfflineSS.js"),
                script(src := s"${webappPath1}RestDataSourceSS.js"),
                script(src := s"${webappPath1}JSONSS.js"),
                script(src := s"${webappPath1}system/ClassSS.js"),
                script(src := s"${webappPath1}foundation/CanvasSS.js"),
                script(src := s"${webappPath1}foundation/JoinJSCanvas.js"),
                script(src := s"${webappPath1}layout/LayoutSS.js"),
                script(src := s"${webappPath1}LayoutSpacerSS.js"),
                script(src := s"${webappPath1}DynamicFormSS.js"),
                script(src := s"${webappPath1}HTMLPaneItem.js"),
                script(src := s"${webappPath1}FormItems/IButtonItem.js"),
                script(src := s"${webappPath1}layout/TabSetSS.js"),
                script(src := s"${webappPath1}layout/VStackSS.js"),
                script(src := s"${webappPath1}layout/HStackSS.js"),
                script(src := s"${webappPath1}layout/VLayoutSS.js"),
                script(src := s"${webappPath1}layout/HLayoutSS.js"),
                script(src := s"${webappPath1}layout/HTMLPaneSS.js"),
                script(src := s"${webappPath1}layout/IconMenuButtonSS.js"),
                script(src := s"${webappPath1}control/IButtonSS.js"),
                script(src := s"${webappPath1}FormItems/ComboboxItemWithButtons.js"),
                script(src := s"${webappPath1}FormItems/ComboboxItemWithClearButton.js"),
                script(src := s"${webappPath1}IconButtonSS.js"),
                script(src := s"${webappPath1}WindowSS.js"),
                script(src := s"${webappPath1}control/MenuSS.js"),
                script(src := s"${webappPath1}PortletSS.js"),
                script(src := s"${webappPath1}PortalLayoutSS.js"),
                script(src := s"${webappPath1}ListGridSS.js"),
                script(src := s"${webappPath1}ErrorDetailMessage.js"),
                script(src := s"${webappPath1}ErrorMessage.js"),
                script(src := s"${webappPath1}layout/HPanelSS.js"),
                script(src := s"${webappPath1}OkCancelPanel.js"),
                script(src := s"${webappPath1}FunctionPanel.js"),
                script(src := s"${webappPath1}OkCancelFunctionPanel.js"),
                script(src := s"${webappPath1}OkCancelPanelWithOutOwnerDestroy.js"),
                script(src := s"${webappPath1}OkCancelFunctionPanelWithOutOwnerDestroy.js"),
                script(src := s"${webappPath1}layout/ChainMasterDetail.js"),
                script(src := s"${webappPath1}ListGridEditor.js"),
                script(src := s"${webappPath1}TreeGridEditor.js"),
                script(src := s"${webappPath1}TreeListGridEditor.js"),
                script(src := s"${webappPath1}LoginWindow.js"),
                script(src := s"${webappPath1}MessagingSS.js"),
                script(src := s"${webappPath1}WindowWrapper.js"),
                script(src := s"${webappPath1}LookupEditor.js"),

                script(src := s"${webappPath}javascript/generated/generatedComponentsJS/${if (fullOpt) "web-ui-opt.js" else "webuijs-fastopt.js"}"),

                link(href := s"${webappPath}managed/css/common-webapp/logging_styles.css", rel := "stylesheet", `type` := "text/css"),
                script(lastScript)
            )
        )
    }
}
