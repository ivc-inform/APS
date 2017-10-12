package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.asp.OpersType
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

import scala.scalajs.js._

class OpersTypeProps extends CommonListGridEditorComponentProps {
    type classHandler <: OpersType

    identifier = "456F794F-EE8F-C38D-73D6-CF6F0F5170E2".opt

    simpleTable = true.opt

    dataSource = DataSourcesJS.aps_opers_type_DS.opt

    editingFields = FormItemsJS.aps_opers_type_FRMITM.opt
    fields = ListGridFiledsJS.aps_opers_type_FLDS.opt
}
