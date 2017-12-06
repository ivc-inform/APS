package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.formItems.props.{LookupListGridEditorItemProps, LookupTreeGridEditorItemProps}
import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.System.{LookupListGridEditorItem, LookupTreeGridEditorItem}
import com.simplesys.System.Types.{FormItemComponentType, ListGridFieldType}
import com.simplesys.app.{OpersType, Tasks}
import com.simplesys.js.components.asp.Orders
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._

class OrdersProps extends CommonListGridEditorComponentProps {
    type classHandler <: Orders

    identifier = "4EEF794F-EE8F-C38D-73D6-CF1230F5170E2".opt

    simpleTable = true.opt

    dataSource = DataSourcesJS.aps_orders_DS.opt

    editingFields = FormItemsJS.aps_orders_FRMITM.opt
    fields = ListGridFiledsJS.aps_orders_FLDS.opt

    replacingFields = Seq(
        new ListGridFieldProps {
            nameStrong = aps_orders_code_task_Id_task_NameStrong.opt
            `type` = ListGridFieldType.sCaption_SimpleType.opt
            editorType = FormItemComponentType.LookupTreeGridEditorItem
            editorProperties = LookupTreeGridEditorItem(
                new LookupTreeGridEditorItemProps {
                    treeGridEditor = Tasks.create(new TasksProps).opt
                }).opt
        },
        new ListGridFieldProps {
            nameStrong = aps_orders_code_operstype_Oper_type_NameStrong.opt
            `type` = ListGridFieldType.sCaption_SimpleType.opt
            editorType = FormItemComponentType.LookupListGridEditorItem
            editorProperties = LookupListGridEditorItem(
                new LookupListGridEditorItemProps {
                    listGridEditor = OpersType.create(new OpersTypeProps).opt
                }).opt
        }
    ).opt
}
