package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.formItems.props.{LookupListGridEditorItemProps, LookupTreeGridEditorItemProps}
import com.simplesys.SmartClient.App.props.CommonTreeListGridEditorComponentProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.System.{LookupListGridEditorItem, LookupTreeGridEditorItem, isc}
import com.simplesys.System.Types.{FormItemComponentType, ListGridFieldType}
import com.simplesys.app.{OpersType, Tasks}
import com.simplesys.js.components.asp.TasksOrders
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.FormItemsJS._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.ListGridFiledsJS._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._

class TasksOrdersProps extends CommonTreeListGridEditorComponentProps {
    type classHandler <: TasksOrders

    identifier = "58125E1C-252A-01C4-11A1-667FA3222D3F".opt

    dataSourceList = aps_orders_DS.opt
    dataSourceTree = aps_tasks_DS.opt

    fieldsTree = aps_tasks_FLDS.opt
    editingTreeFields = aps_tasks_FRMITM.opt

    fieldsList = aps_orders_FLDS.opt
    editingListFields = aps_orders_FRMITM.opt

    captionMenuTree = "Задачи".opt
    captionMenuList = "Операции".opt

    val taskEditor = Tasks.create(new TasksProps)

    val operTypeEditor = OpersType.create(new OpersTypeProps)


    replacingFieldsList = Seq(
        new ListGridFieldProps {
            nameStrong = aps_orders_code_task_Id_task_NameStrong.opt
            `type` = ListGridFieldType.sCaption_SimpleType.opt
            editorType = FormItemComponentType.LookupTreeGridEditorItem
            editorProperties = LookupTreeGridEditorItem(
                new LookupTreeGridEditorItemProps {
                    treeGridEditor = taskEditor.opt
                }).opt
        },
        new ListGridFieldProps {
            nameStrong = aps_orders_code_operstype_Oper_type_NameStrong.opt
            `type` = ListGridFieldType.sCaption_SimpleType.opt
            editorType = FormItemComponentType.LookupListGridEditorItem
            editorProperties = LookupListGridEditorItem(
                new LookupListGridEditorItemProps {
                    listGridEditor = operTypeEditor.opt
                }).opt
        }
    ).opt
}
