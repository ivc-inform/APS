package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.formItems.props.{LookupListGridEditorItemProps, LookupTreeGridEditorItemProps}
import com.simplesys.SmartClient.App.props._
import com.simplesys.SmartClient.DataBinding.props.SortSpecifierProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.System.{LookupListGridEditorItem, LookupTreeGridEditorItem, SortSpecifier}
import com.simplesys.System.Types._
import com.simplesys.app._
import com.simplesys.js.components.asp.ResultItem
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._

class ResultItemProps extends CommonListGridEditorComponentProps {
    type classHandler <: ResultItem
    identifier = "4EEF794F-EE8F-C38D-73D6-CF6F0F5170E3".opt

    simpleTable = true.opt

    fields = (ListGridFiledsJS.aps_result_items_FLDS ++ ListGridFiledsJS.aps_changeover_FLDS.filter(item ⇒ item.nameStrong.get.name == aps_changeover_code_operstype_From_type_NameStrong.name || item.nameStrong.get.name == aps_changeover_code_operstype_To_type_NameStrong.name)).map {
        item ⇒
            item.nameStrong.get.name match {
                case aps_changeover_code_operstype_From_type_NameStrong.name ⇒
                    item.title = "Код типа операции до переналадки".opt
                    item.canEdit = false.opt
                    item
                case aps_changeover_code_operstype_To_type_NameStrong.name ⇒
                    item.title = "Код типа  операции после переналадки".opt
                    item.canEdit = false.opt
                    item
                case aps_result_items_opertimestart_NameStrong.name ⇒
                    item.canEdit = true.opt
                    item
                case aps_result_items_opertimeend_NameStrong.name ⇒
                    item.canEdit = true.opt
                    item
                case aps_result_items_scode_Id_result_NameStrong.name ⇒
                    item.hidden = true.opt
                    item
                case _ ⇒
                    item.canEdit = false.opt
                    item
            }
    }.opt

    dataSource = DataSourcesJS.aps_result_items_DS.opt

    //editEvent = ListGridEditEvent.none.opt
    editEvent = ListGridEditEvent.doubleClick.opt

    itemsType = Seq(miNewWithForm(false), miCopy(false), miDelete(false), miEdit(false), miRefresh()).opt

    replacingFields = Seq(
        new ListGridFieldProps {
            nameStrong = aps_result_items_pos_NameStrong.opt
            align = Alignment.center.opt
            `type` = ListGridFieldType.nInt_SimpleType.opt
            canEdit = false.opt
        },
        new ListGridFieldProps {
            nameStrong = aps_result_items_duration_NameStrong.opt
            align = Alignment.center.opt
            `type` = ListGridFieldType.fDouble_SimpleType.opt
            canEdit = false.opt
        },
        new ListGridFieldProps {
            nameStrong = aps_changeover_scode_rc_Idrc_NameStrong.opt
            align = Alignment.center.opt
            editorType = FormItemComponentType.LookupListGridEditorItem
            editorProperties = LookupListGridEditorItem(new LookupListGridEditorItemProps {
                listGridEditor = Rc.create(new RcProps).opt
            }).opt
            canEdit = false.opt
        },
        new ListGridFieldProps {
            nameStrong = aps_result_items_code_orders_Id_orders_NameStrong.opt
            align = Alignment.center.opt
            editorType = FormItemComponentType.LookupListGridEditorItem
            editorProperties = LookupListGridEditorItem(new LookupListGridEditorItemProps {
                listGridEditor = Orders.create(new OrdersProps {
                    identifier = "7DDBAEBA-DEDD-267F-95E6-F5F6EB36A122".opt
                }).opt
            }).opt
            canEdit = false.opt
        },
        new ListGridFieldProps {
            nameStrong = aps_orders_code_task_Id_task_NameStrong.opt
            `type` = ListGridFieldType.sCaption_SimpleType.opt
            editorType = FormItemComponentType.LookupTreeGridEditorItem
            editorProperties = LookupTreeGridEditorItem(
                new LookupTreeGridEditorItemProps {
                    treeGridEditor = Tasks.create(new TasksProps).opt
                }).opt
            canEdit = false.opt
        }
    ).opt

    canSort = false.opt

    initialSort = Seq(
        SortSpecifier(
            new SortSpecifierProps {
                property = aps_result_items_pos_NameStrong.name.opt
                direction = SortDirection.ascending.opt
            }
        )
    ).opt

}
