package com.ivcInform.app.http

import com.simplesys.SmartClient.App.{SettingsEditor, WebTabSetApp}
import com.simplesys.SmartClient.App.props.SettingsEditorProps
import com.simplesys.SmartClient.System.SettingsEditor
import com.simplesys.function._
import com.simplesys.option.ScOption._

class ApsWindowMain extends WebTabSetApp {
    self ⇒

    override protected val loadSchemas = false
    override protected val identifier = "5814FE1C-252A-01C4-11A1-557FA3345D3F"
    override protected val appImageDir = "images/"

    override protected val dataSourcesJS_admin_UserGroup_DS = _
    override protected val dataSourcesJS_admin_User_DS = _

    override protected val listGridFiledsJS_admin_UserGroup_FLDS = _
    override protected val listGridFiledsJS_admin_User_FLDS = _

    override protected val formItemsJS_admin_UserGroup_FRMITM = _
    override protected val formItemsJS_admin_User_FRMITM = _

    override protected val admin_User_codeGroup_NameStrong = _

    override protected val managedUsersGroups = _
    override protected val progectManagedDevsGroups = _

    override protected def getSettingsEditor(): SettingsEditor = SettingsEditor.create(
        new SettingsEditorProps {
            identifier = self.identifier.opt
        }
    )
}
