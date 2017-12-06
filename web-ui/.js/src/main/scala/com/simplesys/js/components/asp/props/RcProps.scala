package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.formItems.props.LookupTreeGridEditorItemProps
import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.System.LookupTreeGridEditorItem
import com.simplesys.System.Types.{FormItemComponentType, ListGridFieldType}
import com.simplesys.app.Tasks
import com.simplesys.js.components.asp.Rc
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS, aps_orders_code_task_Id_task_NameStrong}

class RcProps extends CommonListGridEditorComponentProps {
    type classHandler <: Rc
    identifier = "4EEF794F-EE8F-C38D-73D6-CF6F0F5170E2".opt

    simpleTable = true.opt

    dataSource = DataSourcesJS.aps_rc_DS.opt

    editingFields = FormItemsJS.aps_rc_FRMITM.opt
    fields = ListGridFiledsJS.aps_rc_FLDS.opt

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
