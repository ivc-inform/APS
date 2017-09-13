package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.asp.Rc
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

class RcProps extends CommonListGridEditorComponentProps {
    type classHandler <: Rc
    identifier = "4EEF794F-EE8F-C38D-73D6-CF6F0F5170E2".opt

    simpleTable = true.opt

    dataSource = DataSourcesJS.math_rc_DS.opt

    editingFields = FormItemsJS.math_rc_FRMITM.opt
    fields = ListGridFiledsJS.math_rc_FLDS.opt
}
