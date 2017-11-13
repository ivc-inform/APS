package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.asp.Orders
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

class OrdersProps extends CommonListGridEditorComponentProps {
    type classHandler <: Orders

    identifier = "4EEF794F-EE8F-C38D-73D6-CF1230F5170E2".opt

    simpleTable = true.opt

    dataSource = DataSourcesJS.aps_orders_DS.opt

    editingFields = FormItemsJS.aps_orders_FRMITM.opt
    fields = ListGridFiledsJS.aps_orders_FLDS.opt
}
