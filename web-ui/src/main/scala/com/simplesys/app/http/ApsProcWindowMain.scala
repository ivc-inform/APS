package com.simplesys.app.http

import com.simplesys.SmartClient.App.props.{EditorUserGroupsProps, SettingsEditorProps}
import com.simplesys.SmartClient.App.{SettingsEditor, WebTabSetApp}
import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.MenuSSProps
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.DataBinding.RestDataSourceSS
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Foundation.Canvas
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

    override protected val admin_User_codeGroup_NameStrong: NameStrong = ScalaJSGen.admin_User_codeGroup_Group_NameStrong
    override protected val admin_User_captionGroup_NameStrong: NameStrong = ScalaJSGen.admin_User_captionGroup_Group_NameStrong

    override protected def getSettingsEditor(): SettingsEditor = SettingsEditor.create(
        new SettingsEditorProps {
            identifier = self.identifier.opt
        }
    )

    protected val managedUsersGroups: Seq[RibbonGroupSS] = Seq(
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Пользователи".ellipsis.opt
                controls = Seq(
                    IconMenuButtonSS.create(
                        new IconMenuButtonSSProps {
                            title = "Расписание".ellipsis.opt
                            icon = app.dictionary.opt
                            identifier = "35736AF3-8813-87B6-8193-A41A9F9BE125".opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq(
                                        new MenuSSItemProps {
                                            name = "orders".opt
                                            icon = app.zapros.opt
                                            title = "Задачи".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(TasksLayout.create(new TasksLayoutProps), item)
                                            }.toFunc.opt
                                        }/*,
                                        new MenuSSItemProps {
                                            name = "rc".opt
                                            icon = app.doctypes.opt
                                            title = "Типы операций".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(OpersType.create(new OpersTypeProps), item)

                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "prodCalendar".opt
                                            icon = app.cards.opt
                                            title = "Производственный календарь".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(ProdCalendar.create(new ProdCalendarProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "changeOver".opt
                                            icon = app.doccats.opt
                                            title = "Матрица наладки".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(ChangeOver.create(new ChangeOverProps), item)

                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "rc".opt
                                            icon = app.accounts.opt
                                            title = "Рабочие центры".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(Rc.create(new RcProps), item)

                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "parametrs".opt
                                            icon = app.properties.opt
                                            title = "Параметры".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(Parametrs.create(new ParametrsProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "result".opt
                                            icon = app.state.opt
                                            title = "Результаты расчетов".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(Result.create(new ResultProps), item)
                                            }.toFunc.opt
                                        }*/
                                    ).opt
                                }
                            ).opt
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
