package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.formItems.props.LookupTreeGridEditorItemProps
import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{FormItemComponentType, ListGridFieldType}
import com.simplesys.System._
import com.simplesys.app.Tasks
import com.simplesys.function._
import com.simplesys.js.components.asp.Parametrs
import com.simplesys.option.ScOption._
import com.simplesys.option.DoubleType._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS, aps_orders_code_task_Id_task_NameStrong}

import scala.scalajs.js._

class ParametrsProps extends CommonListGridEditorComponentProps {
    type classHandler <: Parametrs
    identifier = "4EEF794F-EE8F-C38D-73D6-CF6F0F5170E4".opt

    simpleTable = true.opt

    dataSource = DataSourcesJS.aps_params_DS.opt

    editingFields = FormItemsJS.aps_params_FRMITM.opt
    fields = ListGridFiledsJS.aps_params_FLDS.opt

    editWindowProperties = System.WindowSS(
        new WindowSSProps {
            width = 285
            height = 500
        }
    ).opt

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
