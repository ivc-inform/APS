package com.ivcInform.app.http

import com.simplesys.SmartClient.App.{SettingsEditor, WebTabSetApp}
import com.simplesys.SmartClient.App.props.SettingsEditorProps
import com.simplesys.SmartClient.System.SettingsEditor
import com.simplesys.System.jSUndefined
import com.simplesys.function._
import com.simplesys.option.ScOption._

import scala.scalajs.js
import com.simplesys.System._

class ApsWindowMain extends WebTabSetApp {
    self ⇒

    override protected val loadSchemas = false
    override protected val identifier = "5814FE1C-252A-01C4-11A1-557FA3345D3F"
    override protected val appImageDir = "images/"

    override protected val dataSourcesJS_admin_UserGroup_DS = jSUndefined
    override protected val dataSourcesJS_admin_User_DS = jSUndefined

    override protected val listGridFiledsJS_admin_UserGroup_FLDS = Seq()
    override protected val listGridFiledsJS_admin_User_FLDS = Seq()

    override protected val formItemsJS_admin_UserGroup_FRMITM = Seq()
    override protected val formItemsJS_admin_User_FRMITM = Seq()

    override protected val admin_User_codeGroup_NameStrong = jSUndefined

    override protected val managedUsersGroups = Seq()
    override protected val progectManagedDevsGroups = Seq()

    override protected def getSettingsEditor(): SettingsEditor = SettingsEditor.create(
        new SettingsEditorProps {
            identifier = self.identifier.opt
        }
    )

    @JSExportTopLevel("GetUIContent")
    def GetUIContent(): Unit = {
        getUIContent()
    }
}
