package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.formItems.props.LookupTreeGridEditorItemProps
import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.System
import com.simplesys.SmartClient.System.LookupTreeGridEditorItem
import com.simplesys.System.Types.{FormItemComponentType, ListGridFieldType}
import com.simplesys.app.Tasks
import com.simplesys.js.components.asp.OpersType
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._

class OpersTypeProps extends CommonListGridEditorComponentProps {
    type classHandler <: OpersType

    identifier = "456F794F-EE8F-C38D-73D6-CF6F0F5170E2".opt

    simpleTable = true.opt

    dataSource = DataSourcesJS.aps_opers_type_DS.opt

    editingFields = FormItemsJS.aps_opers_type_FRMITM.opt
    fields = ListGridFiledsJS.aps_opers_type_FLDS.opt
    
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
