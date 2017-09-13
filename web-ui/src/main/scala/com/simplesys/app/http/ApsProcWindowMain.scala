package com.simplesys.app.http

import com.simplesys.SmartClient.App.props.SettingsEditorProps
import com.simplesys.SmartClient.App.{SettingsEditor, WebTabSetApp}
import com.simplesys.SmartClient.DataBinding.RestDataSourceSS
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Layout.RibbonGroupSS
import com.simplesys.SmartClient.Layout.props._
import com.simplesys.SmartClient.System.{RibbonGroupSS, _}
import com.simplesys.System.Types.{State ⇒ _, _}
import com.simplesys.System._
import com.simplesys.app
import com.simplesys.app._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.js.components.asp.props._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

import scala.scalajs.js.annotation.JSExportTopLevel

//@JSExport
object ApsProcWindowMain extends WebTabSetApp {

    self ⇒

    override protected val progectManagedDevsGroups: Seq[RibbonGroupSS] = Seq.empty

    override protected val loadSchemas = com.simplesys.app.loadSchemas
    override protected val identifier: ID = "5814FE1C-252A-01C4-11A1-557FA3222D3F"
    override protected val appImageDir: String = "images/"


    override protected val dataSourcesJS_admin_UserGroup_DS: RestDataSourceSS = DataSourcesJS.admin_UserGroup_DS
    override protected val dataSourcesJS_admin_User_DS: RestDataSourceSS = DataSourcesJS.admin_User_DS

    override protected val listGridFiledsJS_admin_UserGroup_FLDS: Seq[ListGridFieldProps] = ListGridFiledsJS.admin_UserGroup_FLDS
    override protected val listGridFiledsJS_admin_User_FLDS: Seq[ListGridFieldProps] = ListGridFiledsJS.admin_User_FLDS

    override protected val formItemsJS_admin_UserGroup_FRMITM: Seq[FormItem] = FormItemsJS.admin_UserGroup_FRMITM
    override protected val formItemsJS_admin_User_FRMITM: Seq[FormItem] = FormItemsJS.admin_User_FRMITM

    override protected val admin_User_codeGroup_NameStrong: NameStrong = ScalaJSGen.admin_User_codeGroup_NameStrong


    override protected def getSettingsEditor(): SettingsEditor = SettingsEditor.create(
        new SettingsEditorProps {
            identifier = self.identifier.opt
        }
    )

    val widthButton = 100
    protected val managedUsersGroups: Seq[RibbonGroupSS] = Seq(
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Пользователи".ellipsis.opt
                controls = Seq(
                    IconButtonSS.create(
                        new IconButtonSSProps {
                            title = "Производственный <br/>календарь".ellipsis.opt
                            autoFit = false.opt
                            icon = app.cards.opt
                            identifier = "12EE1839-8D4D-FFA0-E491-22B54F55772A".opt
                            width = widthButton
                            click = {
                                (thiz: classHandler) =>
                                    addTab(ProdCalendar.create(new ProdCalendarProps), thiz)
                                    false
                            }.toThisFunc.opt
                        }
                    ),
                    IconButtonSS.create(
                        new IconButtonSSProps {
                            title = "Матрица <br/>наладки".ellipsis.opt
                            icon = app.doccats.opt
                            width = widthButton
                            autoFit = false.opt
                            identifier = "12E56839-8D4D-FFA0-E491-22B54F55772A".opt
                            click = {
                                (thiz: classHandler) =>
                                    addTab(ChangeOver.create(new ChangeOverProps), thiz)
                                    false
                            }.toThisFunc.opt
                        }
                    ),
                    IconButtonSS.create(
                        new IconButtonSSProps {
                            title = "Рабочие <br/>центры".ellipsis.opt
                            icon = app.accounts.opt
                            width = widthButton
                            autoFit = false.opt
                            identifier = "12E56839-994D-FFA0-E491-22B54F55772A".opt
                            click = {
                                (thiz: classHandler) =>
                                    addTab(Rc.create(new RcProps), thiz)
                                    false
                            }.toThisFunc.opt
                        }
                    ),
                    IconButtonSS.create(
                        new IconButtonSSProps {
                            title = "Параметры".ellipsis.opt
                            icon = app.properties.opt
                            width = widthButton
                            autoFit = false.opt
                            identifier = "12E55439-994D-FFA0-E491-22B54F55772A".opt
                            click = {
                                (thiz: classHandler) =>
                                    addTab(Parametrs.create(new ParametrsProps), thiz)
                                    false
                            }.toThisFunc.opt
                        }
                    ),
                    IconButtonSS.create(
                        new IconButtonSSProps {
                            title = "Варианты".ellipsis.opt
                            icon = app.state.opt
                            autoFit = false.opt
                            width = widthButton
                            identifier = "12E12339-994D-FFA0-E491-22B54F55772A".opt
                            click = {
                                (thiz: classHandler) =>
                                    addTab(Result.create(new ResultProps), thiz)
                                    false
                            }.toThisFunc.opt
                        }
                    )
                ).opt
            }
        )
    ).map {
        item =>
            item.hide()
            item
    }

    @JSExportTopLevel("GetUIContent")
    def GetUIContent(): Unit = {
        getUIContent()
    }
}
