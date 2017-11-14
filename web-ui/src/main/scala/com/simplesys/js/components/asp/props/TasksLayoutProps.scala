package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.GridContextMenuData
import com.simplesys.SmartClient.App.props.CompoundGridsContextMenuProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.ListGridEditor
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.props.{ChainMasterDetailProps, TabSetSSProps}
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.System.{Tab, _}
import com.simplesys.System.Types.{HTMLString, ID}
import com.simplesys.System._
import com.simplesys.app
import com.simplesys.app._
import com.simplesys.function._
import com.simplesys.js.components.asp.TasksLayout
import com.simplesys.option.DoubleType._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

import scala.scalajs.js.ThisFunction1

class TasksLayoutProps extends ChainMasterDetailProps {
    type classHandler <: TasksLayout

    identifier = "6256A539-4BE7-227E-2507-25896B994FC6".opt

    /*var setFuncMenu: ScOption[ThisFunction1[classHandler, IscArray[Tab], _]] = {
        (thiz: classHandler, tabs: IscArray[Tab]) ⇒
            val a: JSArray[GridContextMenuData] = tabs.map(
                tab ⇒
                    if (tab.pane.isDefined)
                        Some(new GridContextMenuData {
                            override val captionMenu = tab.title
                            override val grid = tab.pane.get.asInstanceOf[ListGridEditor]
                            override val customMenu = Seq()
                        })
                    else
                        None
            ).filter(_.isDefined)

            tab.pane.foreach {
                pane ⇒
                    val _pane = pane.asInstanceOf[ListGridEditor]

                //isc debugTrap a


            }

    }.toThisFunc.opt*/

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

            thizTop.funcMenu =
              CompoundGridsContextMenu.create(
                  new CompoundGridsContextMenuProps {
                      gridsContextMenuData = Seq(
                          new GridContextMenuData {
                              override val captionMenu = "Задачи"
                              override val grid = tasks.asInstanceOf[ListGridEditor]
                              override val customMenu = Seq()
                          },
                          new GridContextMenuData {
                              override val captionMenu = tabSet.getSelectedTab().get.title
                              override val grid = tabSet.getSelectedTab().get.pane.get.asInstanceOf[ListGridEditor]
                              override val customMenu = Seq()
                          }
                      ).opt
                  }
              )

            thizTop.getViewState()

    }.toThisFunc.opt
}
