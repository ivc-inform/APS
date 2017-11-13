package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.props.TreeListGridContextMenuProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.ListGridEditor
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.props.{ChainMasterDetailProps, HLayoutSSProps, TabSetSSProps}
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.System.Tab
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.ID
import com.simplesys.System._
import com.simplesys.app
import com.simplesys.app._
import com.simplesys.function._
import com.simplesys.js.components.asp.TasksLayout
import com.simplesys.option.ScOption._
import com.simplesys.option.DoubleType._

import scala.scalajs.js._

class TasksLayoutProps extends ChainMasterDetailProps {
    type classHandler <: TasksLayout

    identifier = "6256A539-4BE7-227E-2507-25896B994FC6".opt

    initWidget = {
        (thizTop: classHandler, args: IscArray[JSAny]) ⇒
            thizTop.Super("initWidget", args)

            val tasks = Tasks.create(new TasksProps {
                identifier = s"${thizTop.identifier}_tasks".opt
                width = "20%"
            })
            tasks.showResizeBar = true
            thizTop addMember tasks

            val tabSet = TabSetSS.create(
                new TabSetSSProps {
                    identifier = s"${thizTop.identifier}_tabSet".opt
                    width = "*"
                    canCloseTabs = false.opt
                    tabSelected = {
                        (thiz: classHandler, tabNum: Int, tabPane: Canvas, id: JSUndefined[ID], tab: Tab, name: JSUndefined[String]) ⇒
                            tab.pane.foreach {
                                pane ⇒
                                    val _pane = pane.asInstanceOf[ListGridEditor]
                                    thizTop setFuncMenu TreeListGridContextMenu.create(
                                        new TreeListGridContextMenuProps {
                                            captionMenuTree = "Задачи".opt
                                            captionMenuList = tab.title.opt
                                            simpleTableList = _pane.simpleTable.opt
                                            simpleTableTree = tasks.simpleTable.opt
                                            customMenuList = Seq().opt
                                            customMenuTree = Seq().opt
                                            owner = thiz.opt
                                        }
                                    )
                            }
                            true
                    }.toThisFunc.opt
                    tabs = Seq(
                        Tab(
                            new TabProps {
                                name = "rc".opt
                                pane = OpersType.create(
                                    new OpersTypeProps {
                                        masterGrid = tasks.listGrid.opt
                                    }
                                ).opt
                                title = "Типы операций".opt
                                icon = app.doctypes.opt
                            }
                        ),
                        Tab(
                            new TabProps {
                                name = "prodCalendar".opt
                                icon = app.cards.opt
                                title = "Производственный календарь".ellipsis.opt
                                pane = ProdCalendar.create(
                                    new ProdCalendarProps {
                                        masterGrid = tasks.listGrid.opt
                                    }).opt
                            }
                        ),
                        Tab(
                            new TabProps {
                                name = "changeOver".opt
                                icon = app.doccats.opt
                                title = "Матрица наладки".ellipsis.opt
                                pane = ChangeOver.create(new ChangeOverProps {
                                    masterGrid = tasks.listGrid.opt
                                }).opt
                            }
                        ),
                        Tab(
                            new TabProps {
                                name = "rc".opt
                                icon = app.accounts.opt
                                title = "Рабочие центры".ellipsis.opt
                                pane = Rc.create(new RcProps {
                                    masterGrid = tasks.listGrid.opt
                                }).opt
                            }
                        ),
                        Tab(
                            new TabProps {
                                name = "parametrs".opt
                                icon = app.properties.opt
                                title = "Параметры".ellipsis.opt
                                pane = Parametrs.create(new ParametrsProps {
                                    masterGrid = tasks.listGrid.opt
                                }).opt
                            }
                        ),
                        Tab(
                            new TabProps {
                                name = "result".opt
                                icon = app.state.opt
                                title = "Результаты расчетов".ellipsis.opt
                                pane = Result.create(new ResultProps {
                                    masterGrid = tasks.listGrid.opt
                                }).opt
                            }
                        )
                    ).opt
                }
            )

            thizTop addMember tabSet

            thizTop.getViewState()

    }.toThisFunc.opt
}
