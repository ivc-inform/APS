package com.simplesys.app.http

import com.simplesys.SmartClient.App.props.SettingsEditorProps
import com.simplesys.SmartClient.App.{SettingsEditor, WebTabSetApp}
import com.simplesys.SmartClient.DataBinding.RestDataSourceSS
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Foundation.props.{HTMLPaneProps, HTMLPaneSSProps}
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Layout.RibbonGroupSS
import com.simplesys.SmartClient.Layout.props.{IconButtonSSProps, RibbonGroupSSProps}
import com.simplesys.SmartClient.System.{Common, IconButtonSS, RibbonGroupSS, SettingsEditor, _}
import com.simplesys.System.{JSAny, NameStrong}
import com.simplesys.System.Types.{ID, Visibility}
import com.simplesys.app.GanttChart
import com.simplesys.function._
import com.simplesys.js.components.gantt.{DataStructItem, GanttDataItem}
import com.simplesys.option.DoubleType._
import com.simplesys.js.components.gantt.props.GanttChartProps
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

import scala.scalajs.js
import scala.scalajs.js.Date
import scala.scalajs.js.annotation.JSExportTopLevel

object TestDiagramPage extends WebTabSetApp {
    self ⇒

    override protected val loadSchemas = com.simplesys.app.loadSchemas
    override protected val identifier: ID = "24115187-2DC0-8A44-C723-C1A79C9050D9"
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

    override protected val managedUsersGroups = Seq(
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "TestDiagrams".ellipsis.opt
                //visibility = Visibility.hidden.opt
                controls = Seq(
                    IconButtonSS.create(
                        new IconButtonSSProps {
                            identifier = "BA4E6DF4-FE83-FE30-0782-6E1BA40CA422".opt
                            title = "Gantt Test".opt
                            icon = Common.upload.opt
                            orientation = "gorizontal".opt
                            click = {
                                (thiz: classHandler) =>
                                    addTab(
                                        GanttChart.create(
                                            new GanttChartProps {
                                                identifier = "354E6DF4-FE83-FE30-0782-6E1BA40CA422".opt
                                                height = "100%"
                                                canvasWidth = (Page.getWidth() - 125).opt
                                                data = js.Array(
                                                    new DataStructItem {
                                                        override val id = 1
                                                        override val name = "Задача 1"
                                                        override val series: js.Array[_ <: GanttDataItem] = js.Array(
                                                            new GanttDataItem {
                                                                override val name = "Planned"
                                                                override val start = new Date(2010, 0, 1)
                                                                override val end = new Date(2010, 0, 3)
                                                            },
                                                            new GanttDataItem {
                                                                override val name = "Actual"
                                                                override val start = new Date(2010, 0, 2)
                                                                override val end = new Date(2010, 0, 5)
                                                                override val color = "#f0f0f0"
                                                            }
                                                        )
                                                    },
                                                    new DataStructItem {
                                                        override val id = 2
                                                        override val name = "Feature 2"
                                                        override val series: js.Array[_ <: GanttDataItem] = js.Array(
                                                            new GanttDataItem {
                                                                override val name = "Planned"
                                                                override val start = new Date(2010, 0, 5)
                                                                override val end = new Date(2010, 0, 20)
                                                            },
                                                            new GanttDataItem {
                                                                override val name = "Actual"
                                                                override val start = new Date(2010, 0, 6)
                                                                override val end = new Date(2010, 0, 17)
                                                                override val color = "#f0f0f0"
                                                            },
                                                            new GanttDataItem {
                                                                override val name = "Projected"
                                                                override val start = new Date(2010, 0, 6)
                                                                override val end = new Date(2010, 0, 18)
                                                                override val color = "#e0e0e0"
                                                            }
                                                        )
                                                    }
                                                ).opt
                                                /*data = js.Array(
                                                    new DataStructItem {
                                                        override val series = js.Array(
                                                            new GanttDataItem {
                                                                override val name = "Задача №1"
                                                                override val start = new Date(2011, 8, 1)
                                                                override val end = new Date(2011, 8, 23)
                                                            },
                                                            new GanttDataItem {
                                                                override val name = "Задача №2"
                                                                override val start = new Date(2011, 8, 2)
                                                                override val end = new Date(2011, 8, 5)
                                                                override val color = "#f0f0f0"
                                                            }, new GanttDataItem {
                                                                override val name = "Задача №3"
                                                                override val start = new Date(2011, 8, 1)
                                                                override val end = new Date(2011, 8, 10)
                                                                override val color = "#e0e0e0"
                                                            }
                                                        )
                                                    }
                                                ).opt*/
                                            }
                                        ), thiz)
                                    false
                            }.toThisFunc.opt
                        }
                    )
                ).opt
            }
        )
    )

    override protected val progectManagedDevsGroups = Seq.empty[RibbonGroupSS]

    @JSExportTopLevel("GetTestDiagramPageUIContent")
    def GetUIContent(): Unit = {
        getUIContent()
    }
}
