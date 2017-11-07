package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.formItems.props.LookupListGridEditorItemProps
import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.System.LookupListGridEditorItem
import com.simplesys.System.Types._
import com.simplesys.app.{OpersType, Rc}
import com.simplesys.js.components.asp.ChangeOver
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._

class ChangeOverProps extends CommonListGridEditorComponentProps {
    type classHandler <: ChangeOver
    identifier = "4EEF794F-EE8F-C38D-73D6-CF6F0F5170E1".opt

    simpleTable = true.opt

    dataSource = DataSourcesJS.aps_changeover_DS.opt

    editingFields = FormItemsJS.aps_changeover_FRMITM.opt
    fields = ListGridFiledsJS.aps_changeover_FLDS.opt

    val rcEditor = Rc.create(new RcProps)

    val rcFilterEditor = Rc.create(
        new RcProps {
            selectionAppearance = SelectionAppearance.checkbox.opt
            selectionType = SelectionStyle.multiple.opt
            identifier = "7016FFF2-D154-AE84-B63F-CCA5A7D07650".opt
        }
    )

    replacingFields = Seq(
        new ListGridFieldProps {
            nameStrong = aps_changeover_duration_NameStrong.opt
            `type` = ListGridFieldType.fDouble_SimpleType.opt
            align = Alignment.center.opt
        },
        new ListGridFieldProps {
            nameStrong = aps_changeover_scode_rc_Idrc_NameStrong.opt
            align = Alignment.center.opt
            filterEditorType = FormItemComponentType.LookupListGridEditorItem
            filterEditorProperties = LookupListGridEditorItem(new LookupListGridEditorItemProps {
                listGridEditor = rcFilterEditor.opt
            }).opt
            editorType = FormItemComponentType.LookupListGridEditorItem
            editorProperties = LookupListGridEditorItem(new LookupListGridEditorItemProps {
                listGridEditor = rcEditor.opt
            }).opt
        },
        new ListGridFieldProps {
            nameStrong = aps_changeover_code_operstype_From_type_NameStrong.opt
            align = Alignment.center.opt
            `type` = ListGridFieldType.sCaption_SimpleType.opt
            editorType = FormItemComponentType.LookupListGridEditorItem
            editorProperties = LookupListGridEditorItem(new LookupListGridEditorItemProps {
                listGridEditor = OpersType.create(new OpersTypeProps).opt
            }).opt
        },

        new ListGridFieldProps {
            nameStrong = aps_changeover_code_operstype_To_type_NameStrong.opt
            align = Alignment.center.opt
            `type` = ListGridFieldType.sCaption_SimpleType.opt
            editorType = FormItemComponentType.LookupListGridEditorItem
            editorProperties = LookupListGridEditorItem(new LookupListGridEditorItemProps {
                listGridEditor = OpersType.create(new OpersTypeProps{
                    identifier = "7016FFF2-D154-AE84-B63F-CCA5A7D07123".opt
                }).opt
            }).opt
        }
    ).opt
}
