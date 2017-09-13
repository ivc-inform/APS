package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.asp.ProdCalendar
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

class ProdCalendarProps extends CommonListGridEditorComponentProps {
    type classHandler <: ProdCalendar
    identifier = "4EEF794F-EE8F-C38D-73D6-CF6F0F5170EE".opt
    simpleTable = true.opt

    dataSource = DataSourcesJS.math_prod_calendar_DS.opt

    editingFields = FormItemsJS.math_prod_calendar_FRMITM.opt
    fields = ListGridFiledsJS.math_prod_calendar_FLDS.opt
}
