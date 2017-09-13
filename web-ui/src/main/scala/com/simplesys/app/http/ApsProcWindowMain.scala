package com.simplesys.app.http

import com.simplesys.SmartClient.App.props.SettingsEditorProps
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
    
    protected val managedUsersGroups: Seq[RibbonGroupSS] = Seq(
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Пользователи".ellipsis.opt
                controls = Seq(
                    IconButtonSS.create(
                        new IconButtonSSProps {
                            title = "Производственный календарь".ellipsis.opt
                            icon = app.cards.opt
                            identifier = "12EE1839-8D4D-FFA0-E491-22B54F55772A".opt
                            click = {
                                (thiz: classHandler) =>
                                    addTab(ProdCalendar.create(new ProdCalendarProps), thiz)
                                    false
                            }.toThisFunc.opt
                        }
                    ),
                    IconButtonSS.create(
                        new IconButtonSSProps {
                            title = "Матрица наладки".ellipsis.opt
                            icon = app.settings.opt
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
                            title = "Рабочие центры".ellipsis.opt
                            icon = app.accounts.opt
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

        /*RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Пользователи".ellipsis.opt
                controls = Seq(
                    IconMenuButtonSS.create(
                        new IconMenuButtonSSProps {
                            title = "Магнитотека".ellipsis.opt
                            icon = app.cards.opt
                            identifier = "33EE1839-8D4D-FFA0-E491-22B54F55772A".opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq(
                                        new MenuSSItemProps {
                                            name = "cards".opt
                                            icon = app.card.opt
                                            title = "Карточки МГТ".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(Cards.create(new CardsProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "zapros".opt
                                            icon = app.zapros.opt
                                            title = "Запросы".ellipsis.opt
                                            enabled = false.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                //addTab(Zapros.create(new ZaprosProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "docizv".opt
                                            icon = app.docizv.opt
                                            title = "Извещения".ellipsis.opt
                                            enabled = false.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                //addTab(DocIzv.create(new DocIzvProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "inventory".opt
                                            icon = app.inventory.opt
                                            title = "Описи".ellipsis.opt
                                            enabled = false.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                //addTab(DocIzv.create(new DocIzvProps), item)
                                            }.toFunc.opt
                                        }

                                    ).opt
                                }
                            ).opt
                        }
                    )
                ).opt
            }
        ),*/
        /*RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Пользователи".ellipsis.opt
                controls = Seq(
                    IconMenuButtonSS.create(
                        new IconMenuButtonSSProps {
                            title = "Справочники".ellipsis.opt
                            icon = app.ref_RefRefs.opt
                            identifier = "33EE1839-8D4D-FFA0-E491-85B54F2C772A".opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq(
                                        new MenuSSItemProps {
                                            name = "abonents_org".opt
                                            icon = app.organization.opt
                                            title = "Предприятия".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(AbonentsOrg.create(new AbonentsOrgProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "doccats".opt
                                            icon = app.doccats.opt
                                            title = "Виды документов".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(DocCats.create(new DocCatsProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "docformats".opt
                                            icon = app.doctypes.opt
                                            title = "Форматы".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(DocFormats.create(new DocFormatsProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "docitem".opt
                                            icon = app.docitem.opt
                                            title = "Изделия".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(DocItem.create(new DocItemProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "docizvstat".opt
                                            icon = app.status.opt
                                            title = "Статусы извещений".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(DocIzvStat.create(new DocIzvStatProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "docizvtype".opt
                                            icon = app.docizvtype.opt
                                            title = "Типы извещений".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(DocIzvType.create(new DocIzvTypeProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "docliter".opt
                                            icon = app.docliter.opt
                                            title = "Литеры".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(DocLiter.create(new DocLiterProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "doctypes".opt
                                            icon = app.doctypes.opt
                                            title = "Тип документов".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(DocTypes.create(new DocTypesProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "groupitem".opt
                                            icon = app.groupitem.opt
                                            title = "Группы".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(GroupItem.create(new GroupItemProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "mtype".opt
                                            icon = app.mtype.opt
                                            title = "Типы носителя".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(MType.create(new MTypeProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "state".opt
                                            icon = app.state.opt
                                            title = "Состояния".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(State.create(new StateProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "status".opt
                                            icon = app.status.opt
                                            title = "Статус запроса".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(Status.create(new StatusProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "statusversion".opt
                                            icon = app.statusversion.opt
                                            title = "Статус ревизий".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(StatVersion.create(new StatVersionProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "mvid".opt
                                            icon = app.mvid.opt
                                            title = "Вид носителя".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(MVid.create(new MVidProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            isSeparator = true.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "abonents_types".opt
                                            icon = app.abonents.opt
                                            title = "Типы абонентов".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(AbonentsTypes.create(new AbonentsTypesProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "abonents_types".opt
                                            icon = app.admin.opt
                                            title = "Абоненты".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(Abonents.create(new AbonentsProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "attachtypes".opt
                                            icon = app.attach.opt
                                            title = "Тип вложения".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(AttachTypes.create(new AttachTypesProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "austat".opt
                                            icon = app.status.opt
                                            title = "Статусы версий".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(AuStat.create(new AuStatProps), item)
                                            }.toFunc.opt
                                        }
                                    ).opt
                                }
                            ).opt
                        }
                    )
                ).opt
            }
        ),*/
        /*RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Пользователи".ellipsis.opt
                controls = Seq(
                    IconMenuButtonSS.create(
                        new IconMenuButtonSSProps {
                            title = "Выдача копий".ellipsis.opt
                            icon = app.copyProduct.opt
                            identifier = "33121839-8D4D-FFA0-E491-22B54F2C772A".opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq().opt
                                }
                            ).opt
                        }
                    )
                ).opt
            }
        ),*/
        /*RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Пользователи".ellipsis.opt
                controls = Seq(
                    IconMenuButtonSS.create(
                        new IconMenuButtonSSProps {
                            title = "Отчеты".ellipsis.opt
                            icon = app.reports.opt
                            identifier = "33EE1839-810D-FFA0-E491-22B54F2C772A".opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq().opt
                                }
                            ).opt
                        }
                    )
                ).opt
            }
        ),*/

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
