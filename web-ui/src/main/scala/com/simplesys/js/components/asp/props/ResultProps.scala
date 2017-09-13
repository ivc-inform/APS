package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.asp.Result
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

import scala.scalajs.js._

class ResultProps extends CommonListGridEditorComponentProps {
    type classHandler <: Result
    identifier = "4EEF794F-EE8F-C38D-73D6-CF6F0F5170E5".opt

    simpleTable = true.opt

    dataSource = DataSourcesJS.math_result_DS.opt

    editingFields = FormItemsJS.math_result_FRMITM.opt
    fields = ListGridFiledsJS.math_result_FLDS.opt
}
