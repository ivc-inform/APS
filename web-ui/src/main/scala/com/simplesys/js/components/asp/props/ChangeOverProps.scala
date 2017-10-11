package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.formItems.props.LookupListGridEditorItemProps
import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.System.LookupListGridEditorItem
import com.simplesys.System.Types.{FormItemComponentType, SelectionAppearance, SelectionStyle}
import com.simplesys.app.Rc
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
            nameStrong = aps_rc_scode_NameStrong.opt
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
            nameStrong = aps_rc_sname_NameStrong.opt
            filterEditorType = FormItemComponentType.LookupListGridEditorItem
            filterEditorProperties = LookupListGridEditorItem(new LookupListGridEditorItemProps {
                listGridEditor = rcFilterEditor.opt
            }).opt
            editorType = FormItemComponentType.LookupListGridEditorItem
            editorProperties = LookupListGridEditorItem(new LookupListGridEditorItemProps {
                listGridEditor = rcEditor.opt
            }).opt
        }
    ).opt
}
