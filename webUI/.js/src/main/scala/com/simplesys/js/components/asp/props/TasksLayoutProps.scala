package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.GridContextMenuData
import com.simplesys.SmartClient.App.props.CompoundGridsContextMenuProps
import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.{ListGridEditor, TreeGridEditor}
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.props.{ChainMasterDetailProps, TabSetSSProps}
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.RPC.props.RPCRequestProps
import com.simplesys.SmartClient.RPC.{RPCManagerSS, RPCRequest, RPCResponse}
import com.simplesys.SmartClient.System.{Tab, _}
import com.simplesys.System.Types.{ID, SelectionAppearance}
import com.simplesys.System._
import com.simplesys.app
import com.simplesys.app.{Tasks, _}
import com.simplesys.function._
import com.simplesys.isc.dataBinging.DSResponse
import com.simplesys.js.components.asp.{Tasks, TasksLayout}
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.request.CalculateRequest
import com.simplesys.circe.Circe._
import com.simplesys.response.ResponseCalculateData
import io.circe.generic.auto._
import io.circe.scalajs.{convertJsToJson, convertJsonToJs}
import io.circe.syntax._
import io.circe.generic.auto._
import io.circe.scalajs._
import io.circe.syntax._
import ru.simplesys.defs.app.scala.container.TasksDataRecord

import scala.scalajs.js.UndefOr._

class TasksLayoutProps extends ChainMasterDetailProps {
    type classHandler <: TasksLayout

    implicit def canvas2ListGridEditor(canvas: Canvas): ListGridEditor = canvas.asInstanceOf[ListGridEditor]

    identifier = "6256A539-4BE7-227E-2507-25896B994FC6".opt

    var tasks: ScOption[Tasks] = ScNone

    def getMenu(thizTop: classHandler, tab: Tab) = {
        CompoundGridsContextMenu.create(
            new CompoundGridsContextMenuProps {
                gridsContextMenuData = Seq(
                    new GridContextMenuData {
                        override val captionMenu = "Задачи".ellipsis
                        override val grid = thizTop.tasks
                        override val customMenu = Seq(
                            MenuSSItem(
                                new MenuSSItemProps {
                                    title = "Произвести расчет".ellipsis.opt
                                    icon = Common.calc.opt
                                })
                        )
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

    initWidget = {
        (thizTop: classHandler, args: IscArray[JSAny]) ⇒

            thizTop.Super("initWidget", args)

            thizTop.tasks = Tasks.create(new TasksProps {
                identifier = s"${thizTop.identifier}_tasks".opt
                selectionAppearance = SelectionAppearance.checkbox.opt
                //selectionType = SelectionStyle.multiple.opt
                width = "20%"
                customMenu = Seq(
                    MenuSSItem(
                        new MenuSSItemProps {
                            title = "Расчет расписания".ellipsis.opt
                            identifier = "calculate".opt
                            icon = Common.calculator.opt
                            click = {
                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                                    simpleSyS checkOwner owner

                                    RPCManagerSS.sendRequest(
                                        RPCRequest(
                                            new RPCRequestProps {
                                                actionURL = "logic/calculateTask".opt
                                                data = convertJsonToJs(CalculateRequest(idTask = owner.getSelectedRecord.asInstanceOf[TasksDataRecord].idtask.getOrElse(0.0).toLong).asJson).opt
                                                timeout = 60000.opt
                                                sendNoQueue = true.opt
                                                callback = {
                                                    (resp: RPCResponse, data: JSAny, req: RPCRequest) ⇒
                                                        if (resp.httpResponseCode == 200) {
                                                            convertJsToJson(data) match {
                                                                case Right(json) ⇒
                                                                    json.getJsonObject("response").as[DSResponse] match {
                                                                        case Right(dsResponse) ⇒
                                                                            dsResponse.data.as[ResponseCalculateData] match {
                                                                                case Right(result) ⇒
                                                                                    isc ok result.message
                                                                                case Left(failure) ⇒
                                                                                    isc error (failure.getMessage)
                                                                            }
                                                                        case Left(failure) ⇒
                                                                            isc error (failure.getMessage)
                                                                    }
                                                                case Left(failure) ⇒
                                                                    isc error (failure.getMessage)
                                                            }
                                                        }

                                                }.toFunc.opt
                                            }
                                        )
                                    )
                            }.toFunc.opt
                        }
                    )
                ).opt
            })

            thizTop.tasks.showResizeBar = true
            thizTop addMember thizTop.tasks

            val tabSet = TabSetSS.create(
                new TabSetSSProps {
                    identifier = s"${thizTop.identifier}_tabSet".opt
                    width = "*"
                    canCloseTabs = false.opt
                    tabDeselected = {
                        (thiz: classHandler, tabNum: Int, tabPane: Canvas, id: JSUndefined[ID], tab: Tab, newTab: Tab) ⇒
                            simpleSyS.functionButton.foreach(_.menu = getMenu(thizTop, newTab))
                            true
                    }.toThisFunc.opt
                    tabs = Seq(
                        Tab(
                            new TabProps {
                                name = "orders".opt
                                pane = Orders.create(
                                    new OrdersProps {
                                        masterGrid = thizTop.tasks.listGrid.opt
                                    }
                                ).opt
                                title = "Операции".ellipsis.opt
                                icon = app.doccats.opt
                            }
                        ),
                        Tab(
                            new TabProps {
                                name = "rc".opt
                                pane = OpersType.create(
                                    new OpersTypeProps {
                                        masterGrid = thizTop.tasks.listGrid.opt
                                    }
                                ).opt
                                title = "Типы операций".ellipsis.opt
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
                                name = "taskResult".opt
                                icon = app.reports.opt
                                title = "Показатели расчетов".ellipsis.opt
                                pane = TaskResult.create(new TaskResultProps {
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

            thizTop.funcMenu = getMenu(thizTop, tabSet.getSelectedTab().get)

            thizTop.getViewState()

    }.toThisFunc.opt
}
