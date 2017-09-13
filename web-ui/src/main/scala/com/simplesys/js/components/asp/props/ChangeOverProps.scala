package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.asp.ChangeOver
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

import scala.scalajs.js._

class ChangeOverProps extends CommonListGridEditorComponentProps {
    type classHandler <: ChangeOver
    identifier = "4EEF794F-EE8F-C38D-73D6-CF6F0F5170E1".opt

    simpleTable = true.opt

    dataSource = DataSourcesJS.math_changeover_DS.opt

    editingFields = FormItemsJS.math_changeover_FRMITM.opt
    fields = ListGridFiledsJS.math_changeover_FLDS.opt
}
