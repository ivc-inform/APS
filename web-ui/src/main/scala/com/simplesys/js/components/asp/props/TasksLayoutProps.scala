package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.GridContextMenuData
import com.simplesys.SmartClient.App.props.CompoundGridsContextMenuProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.ListGridEditor
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.props.{ChainMasterDetailProps, TabSetSSProps}
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.System.{Tab, _}
import com.simplesys.System.Types.ID
import com.simplesys.System._
import com.simplesys.app
import com.simplesys.app.{Tasks, _}
import com.simplesys.function._
import com.simplesys.js.components.asp.{Tasks, TasksLayout}
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js.UndefOr._

class TasksLayoutProps extends ChainMasterDetailProps {
    type classHandler <: TasksLayout

    implicit def canvas2ListGridEditor(canvas: Canvas): ListGridEditor = canvas.asInstanceOf[ListGridEditor]

    identifier = "6256A539-4BE7-227E-2507-25896B994FC6".opt

    var tasks: ScOption[Tasks] = ScNone

    initWidget = {
        (thizTop: classHandler, args: IscArray[JSAny]) ⇒

            thizTop.Super("initWidget", args)


            thizTop.tasks = Tasks.create(new TasksProps {
                identifier = s"${thizTop.identifier}_tasks".opt
                width = "20%"
            })

            thizTop.tasks.showResizeBar = true
            thizTop addMember thizTop.tasks

            val tabSet = TabSetSS.create(
                new TabSetSSProps {
                    identifier = s"${thizTop.identifier}_tabSet".opt
                    width = "*"
                    canCloseTabs = false.opt
                    tabSelected = {
                        (thiz: classHandler, tabNum: Int, tabPane: Canvas, id: JSUndefined[ID], tab: Tab, name: JSUndefined[String]) ⇒

                            simpleSyS.functionButton.foreach {
                                _.menu =
                                  CompoundGridsContextMenu.create(
                                      new CompoundGridsContextMenuProps {
                                          gridsContextMenuData = Seq(
                                              new GridContextMenuData {
                                                  override val captionMenu = "Задачи"
                                                  override val grid = thizTop.tasks
                                                  override val customMenu = Seq()
                                              },
                                              new GridContextMenuData {
                                                  override val captionMenu = tab.title
                                                  override val grid = tab.pane.get
                                                  override val customMenu = Seq()
                                              }
                                          ).opt
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
                                        masterGrid = thizTop.tasks.listGrid.opt
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
                                        masterGrid = thizTop.tasks.listGrid.opt
                                    }).opt
                            }
                        ),
                        Tab(
                            new TabProps {
                                name = "changeOver".opt
                                icon = app.doccats.opt
                                title = "Матрица наладки".ellipsis.opt
                                pane = ChangeOver.create(new ChangeOverProps {
                                    masterGrid = thizTop.tasks.listGrid.opt
                                }).opt
                            }
                        ),
                        Tab(
                            new TabProps {
                                name = "rc".opt
                                icon = app.accounts.opt
                                title = "Рабочие центры".ellipsis.opt
                                pane = Rc.create(new RcProps {
                                    masterGrid = thizTop.tasks.listGrid.opt
                                }).opt
                            }
                        ),
                        Tab(
                            new TabProps {
                                name = "parametrs".opt
                                icon = app.properties.opt
                                title = "Параметры".ellipsis.opt
                                pane = Parametrs.create(new ParametrsProps {
                                    masterGrid = thizTop.tasks.listGrid.opt
                                }).opt
                            }
                        ),
                        Tab(
                            new TabProps {
                                name = "result".opt
                                icon = app.state.opt
                                title = "Результаты расчетов".ellipsis.opt
                                pane = Result.create(new ResultProps {
                                    masterGrid = thizTop.tasks.listGrid.opt
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
                              override val grid = thizTop.tasks
                              override val customMenu = Seq()
                          },
                          new GridContextMenuData {
                              override val captionMenu = tabSet.getSelectedTab().get.title
                              override val grid = tabSet.getSelectedTab().get.pane.get
                              override val customMenu = Seq()
                          }
                      ).opt
                  }
              )

            thizTop.getViewState()

    }.toThisFunc.opt
}
