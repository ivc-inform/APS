package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.props._
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.ListGridEditEvent
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.asp.ResultItem
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

import scala.scalajs.js._

class ResultItemProps extends CommonListGridEditorComponentProps {
    type classHandler <: ResultItem
    identifier = "4EEF794F-EE8F-C38D-73D6-CF6F0F5170E3".opt

    dataSource = DataSourcesJS.math_result_item_DS.opt

    editingFields = FormItemsJS.math_result_item_FRMITM.opt
    fields = ListGridFiledsJS.math_result_item_FLDS.opt

    editEvent = ListGridEditEvent.none.opt

    itemsType = Seq(miNewWithForm(false), miCopy(false), miDelete(false), miEdit(false), miRefresh()).opt
}
