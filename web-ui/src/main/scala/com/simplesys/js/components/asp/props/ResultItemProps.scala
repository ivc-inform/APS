package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.props._
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.System.Types.{ListGridEditEvent, ListGridFieldType}
import com.simplesys.js.components.asp.ResultItem
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS, aps_result_items_pos_NameStrong}

class ResultItemProps extends CommonListGridEditorComponentProps {
    type classHandler <: ResultItem
    identifier = "4EEF794F-EE8F-C38D-73D6-CF6F0F5170E3".opt

    dataSource = DataSourcesJS.aps_result_items_DS.opt

    editingFields = FormItemsJS.aps_result_items_FRMITM.opt
    fields = ListGridFiledsJS.aps_result_items_FLDS.opt

    editEvent = ListGridEditEvent.none.opt

    itemsType = Seq(miNewWithForm(false), miCopy(false), miDelete(false), miEdit(false), miRefresh()).opt

    replacingFields = Seq(
        new ListGridFieldProps {
            nameStrong = aps_result_items_pos_NameStrong.opt
            `type` = ListGridFieldType.nInt_SimpleType.opt
            canSort = false.opt
        }).opt

    
}
