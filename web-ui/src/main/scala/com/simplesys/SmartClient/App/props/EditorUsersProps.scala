package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.formItems.props.{LookupListGridEditorItemProps, LookupTreeGridEditorItemProps}
import com.simplesys.SmartClient.DataBinding.props.DSRequestProps
import com.simplesys.SmartClient.Forms.formsItems.props.{FormItemProps, PasswordItemProps}
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System.{DSRequest, FormItem, LookupListGridEditorItem, LookupTreeGridEditorItem, PasswordItem, WindowSS}
import com.simplesys.System.Types.{FormItemComponentType, ListGridFieldType}
import com.simplesys.System._
import com.simplesys.app.{OpersType, Tasks}
import com.simplesys.function._
import com.simplesys.js.components.asp.props.{OpersTypeProps, TasksProps}
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{admin_User_password_NameStrong, aps_orders_code_operstype_Oper_type_NameStrong}


trait NewDSRequestData extends JSObject {
    val active: Boolean
}

class EditorUsersProps extends CommonTreeListGridEditorComponentProps {
    identifier = "58125E1C-252A-01C4-11A1-557FA3222D3F".opt

    captionMenuTree = "Группы пользователей".opt
    captionMenuList = "Пользователи".opt

    /*newTreeRequestProperties = {
        (thiz: classHandler) =>
            DSRequest(
                new DSRequestProps {
                    data = (new NewDSRequestData {
                        override val active = true
                    }).opt
                }
            )

    }.toThisFunc.opt*/

    /*newListRequestProperties = {
        (thiz: classHandler) =>
            DSRequest(
                new DSRequestProps {
                    data = (new NewDSRequestData {
                        override val active = true
                    }).opt
                }
            )

    }.toThisFunc.opt*/

    //showListFilterEditor = false.opt

    replacingFieldsList = Seq(
        new ListGridFieldProps {
            nameStrong = admin_User_password_NameStrong.opt
            `type` = ListGridFieldType.sCaption_SimpleType.opt
            canFilter = false.opt
            filterEditorProperties = PasswordItem(
                new PasswordItemProps {
                    disabled = true.opt
                }
            ).opt
        }
    ).opt

    editWindowPropertiesTree = WindowSS(
        new WindowSSProps {
            width = 285
            height = 285
        }
    ).opt
}
