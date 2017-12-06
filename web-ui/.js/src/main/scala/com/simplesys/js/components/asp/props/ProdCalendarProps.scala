package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.formItems.props.LookupTreeGridEditorItemProps
import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.System.LookupTreeGridEditorItem
import com.simplesys.System.Types.{FormItemComponentType, ListGridFieldType}
import com.simplesys.app.Tasks
import com.simplesys.js.components.asp.ProdCalendar
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS, aps_orders_code_task_Id_task_NameStrong}

class ProdCalendarProps extends CommonListGridEditorComponentProps {
    type classHandler <: ProdCalendar
    identifier = "4EEF794F-EE8F-C38D-73D6-CF6F0F5170EE".opt
    simpleTable = true.opt

    dataSource = DataSourcesJS.aps_prod_calendar_DS.opt

    editingFields = FormItemsJS.aps_prod_calendar_FRMITM.opt
    fields = ListGridFiledsJS.aps_prod_calendar_FLDS.opt

    replacingFields = Seq(
        new ListGridFieldProps {
            nameStrong = aps_orders_code_task_Id_task_NameStrong.opt
            `type` = ListGridFieldType.sCaption_SimpleType.opt
            editorType = FormItemComponentType.LookupTreeGridEditorItem
            editorProperties = LookupTreeGridEditorItem(
                new LookupTreeGridEditorItemProps {
                    treeGridEditor = Tasks.create(new TasksProps).opt
                }).opt
        }
    ).opt
}
