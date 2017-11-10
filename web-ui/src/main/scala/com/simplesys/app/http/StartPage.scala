package com.simplesys.app.http

import scalatags.Text.all._
import scalatags.Text.tags2

case class paths(subPath: String, subPath1: String)

class StartPage[Builder, Output <: FragT, FragT](val _title: String) {
    def bodyHTML(lastScript: String, fullOpt: Boolean = false) = {

        val subPaths = paths(subPath = "javascript/generated/generatedComponents", subPath1 = "coffeeScript/main/")
        //val subPaths = paths(subPath = "managed/javascript/isc-components/generated/generatedComponents", subPath1 = "coffeeScript/main/")
        html(lang := "en",
            head(
                tags2.title(_title),
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

                script(src := "managed/javascript/isc-misc/beautify.js"),
                script(src := "managed/javascript/jsgantt-improved/jsgantt.js"),
                script(src := "managed/javascript/common-webapp/generated/generatedComponents/coffeescript/coffeeScript/main/common.js"),

                script(src := s"javascript/generated/generatedComponents/MakeAboutData.js"),

                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/PreDefined.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/system/ArraySS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/system/LogSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/RPCManagerSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/DataSourceSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/commons.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/OfflineSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/RestDataSourceSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/JSONSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/system/ClassSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/foundation/CanvasSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/foundation/JoinJSCanvas.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/layout/LayoutSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/LayoutSpacerSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/DynamicFormSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/HTMLPaneItem.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/FormItems/IButtonItem.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/layout/TabSetSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/layout/VStackSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/layout/HStackSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/layout/VLayoutSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/layout/HLayoutSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/layout/HTMLPaneSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/layout/IconMenuButtonSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/control/IButtonSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/FormItems/ComboboxItemWithButtons.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/FormItems/ComboboxItemWithClearButton.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/IconButtonSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/WindowSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/control/MenuSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/PortletSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/PortalLayoutSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/ListGridSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/ErrorDetailMessage.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/ErrorMessage.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/layout/HPanelSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/OkCancelPanel.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/FunctionPanel.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/OkCancelFunctionPanel.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/OkCancelPanelWithOutOwnerDestroy.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/OkCancelFunctionPanelWithOutOwnerDestroy.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/layout/ChainMasterDetail.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/ListGridEditor.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/TreeGridEditor.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/TreeListGridEditor.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/LoginWindow.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/MessagingSS.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/WindowWrapper.js"),
                script(src := s"${subPaths.subPath}/coffeescript/${subPaths.subPath1}com/simplesys/LookupEditor.js"),

                script(src := s"javascript/generated/generatedComponentsJS/${if (fullOpt) "web-ui-opt.js" else "web-ui-fastopt.js"}"),
                //script(src := "javascript/generated/generatedComponentsJS/web-ui-jsdeps.js"),
                script(src := "javascript/developed/jquery-3.2.1.js"),
                script(src := "javascript/developed/jquery-ui-1.12.1.js"),

                link(href := "managed/css/common-webapp/logging_styles.css", rel := "stylesheet", `type` := "text/css"),
                link(href := "managed/css/jsgantt-improved/jsgantt.css", rel := "stylesheet", `type` := "text/css"),

                link(href := "css/jquery-ui-1.12.1.css", rel := "stylesheet", `type` := "text/css"),
                //link(href := "css/reset.css", rel := "stylesheet", `type` := "text/css"),
                link(href := "css/jquery.ganttView.css", rel := "stylesheet", `type` := "text/css"),

                script(lastScript)
            )
        )
    }
}
