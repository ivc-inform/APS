package com.simplesys.js.components.gantt.props


import com.simplesys.isc.dataBinging.DSResponse
import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.RPC.props.RPCRequestProps
import com.simplesys.SmartClient.RPC.{RPCManagerSS, RPCRequest, RPCResponse}
import com.simplesys.SmartClient.System.{IscArray, RPCRequest, isc}
import com.simplesys.System.{JSAny, JSUndefined}
import com.simplesys.function._
import com.simplesys.js.components.gantt.GanttImprovedChart
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.request.RequestResult
import io.circe.generic.auto._
import io.circe.scalajs._
import io.circe.syntax._
import com.simplesys.circe.Circe._
import org.scalajs.dom
import com.simplesys.gantt.GanttChartCommon._
import com.simplesys.gantt.JS.GanttChart._
import com.simplesys.gantt.{CaptionType, Format, Group, TaskItemExt ⇒ TaskItemExtCC, _}
import com.simplesys.gantt.JS.{GanttChart, GanttChartExt, TaskItemExt}
import com.simplesys.gantt.TaskCssClass._

import scala.language.implicitConversions
import scalatags.Text.all._
//import com.simplesys.gantt.TaskItemExt._ //!! Must be
import com.simplesys.gantt.TaskItemExt._ //!! Must be

class GanttImprovedChartProps extends CanvasProps {
    type classHandler <: GanttImprovedChart
    val EOL = "\n"

    height = "100%"
    getID1 = {
        (thiz: classHandler) ⇒
            s"${thiz.getID}_ganttChartImproved"
    }.toThisFunc.opt

    var idResult: ScOption[Double] = ScNone

    getInnerHTML = {
        (thiz: classHandler) ⇒
            div(
                style := "position:relative",
                id := thiz.getID1,
                `class` := "gantt"
            ).render
    }.toThisFunc.opt


    var canvasWidth: ScOption[Int] = ScNone

    redrawOnResize = false.opt

    draw = {
        (thiz: classHandler, args: JSUndefined[IscArray[JSAny]]) ⇒
            if (!thiz.readyToDraw())
                thiz
            else {
                thiz.Super("draw")

                RPCManagerSS.sendRequest(
                    RPCRequest(
                        new RPCRequestProps {
                            actionURL = "logic/getGanttData".opt
                            data = convertJsonToJs(RequestResult(idResult = thiz.idResult.getOrElse(0.0).toLong).asJson).opt
                            timeout = 60000.opt
                            sendNoQueue = true.opt
                            callback = {
                                (resp: RPCResponse, data: JSAny, req: RPCRequest) ⇒
                                    if (resp.httpResponseCode == 200) {
                                        convertJsToJson(data) match {
                                            case Right(json) ⇒
                                                json.getJsonObject("response").as[DSResponse] match {
                                                    case Right(dsResponse) ⇒
                                                        dsResponse.data.as[Seq[TaskItemExtCC]] match {
                                                            case Right(seq) ⇒
                                                                val div = dom.document.getElementById(thiz.getID1)

                                                                implicit val g = new GanttChartExt(div, Format.hour)
                                                                g.addLang("rus", GanttChart.langRus)
                                                                g setLang "rus"

                                                                g.getDivId.foreach {
                                                                    _ ⇒
                                                                        g setCaptionType CaptionType.Complete
                                                                        g setQuarterColWidth 36
                                                                        g setDateTaskDisplayFormat "day dd mm yyyy HH:MI"
                                                                        g setDateTaskTableDisplayFormat "dd/mm/yyyy HH:MI"
                                                                        g setDayMajorDateDisplayFormat "mon yyyy - Week ww"
                                                                        g setWeekMinorDateDisplayFormat "dd mon"
                                                                        g setShowTaskInfoLink 1
                                                                        g setShowEndWeekDate 0
                                                                        g setUseSingleCell 20000
                                                                        g setShowComp 1
                                                                        g setFormatArr(Format.hour, Format.day)

                                                                        seq.foreach(item ⇒ g.AddTaskItem(item))
//
//                                                                        seq.foreach {
//                                                                            item ⇒
//                                                                                val a:TaskItemExt = taskItem2JS(item)
//                                                                                println(a.toString)
//                                                                                println(item.asJson.spaces41)
//
//                                                                        }

//                                                                        println("/////////////////////////////////////////////////////////////////// Begin //////////////////////////////////////////////////////////////////////////////////////////////////////")
//                                                                        println(isc.debugTrap(new TaskItemExt(pID = 1, pName = "Define Chart API", pClass = ggroupblack, pRes = "Brian", pGroup = Group.standardGroupTask, pNotes = "Some Notes text")).toString)
//                                                                        println("//////////////////////////////////////////////////////////////////// End  /////////////////////////////////////////////////////////////////////////////////////////////////////")

//                                                                        g.AddTaskItem(new TaskItemExt(pID = 1, pName = "Define Chart API", pClass = ggroupblack, pRes = "Brian", pGroup = Group.standardGroupTask, pNotes = "Some Notes text"))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 11, pName = "Chart Object", pStart = "2016-02-20 12:30".toLDT, pEnd = "2016-02-22 01:22".toLDT, pClass = gmilestone, pLink = Link("Link about"), pMile = MileStone.milestone, pRes = "Shlomy", pComp = 100, pParent = 1))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 12, pName = "Task Objects", pClass = ggroupblack, pRes = "Shlomy", pComp = 40, pGroup = Group.standardGroupTask, pParent = 1, pOpen = Opening.open))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 121, pName = "Constructor Proc", pStart = "2016-02-21 00:00".toLDT, pEnd = "2016-03-09 00:00".toLDT, pClass = gtaskblue, pRes = "Brian T.", pComp = 60, pParent = 12, pOpen = Opening.open))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 122, pName = "Task Variables", pStart = "2016-03-06 00:00".toLDT, pEnd = "2016-03-11 00:00".toLDT, pClass = gtaskred, pRes = "Brian", pComp = 60, pParent = 12, pOpen = Opening.open, pDepend = 121))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 123, pName = "Task by Minute/Hour", pStart = "2016-03-09 00:00".toLDT, pEnd = "2016-03-14 00:00".toLDT, pClass = gtaskyellow, pRes = "Ilan", pComp = 60, pParent = 12, pOpen = Opening.open))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 124, pName = "Task Functions", pStart = "2016-03-09 00:00".toLDT, pEnd = "2016-03-29 00:00".toLDT, pClass = gtaskred, pRes = "Anyone", pComp = 60, pParent = 12, pOpen = Opening.open, pDepend = (123).SS, pCaption = "This is a caption"))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 2, pName = "Create HTML Shell", pStart = "2016-03-24 00:00".toLDT, pEnd = "2016-03-24 00:00".toLDT, pClass = gtaskyellow, pRes = "Brian", pComp = 20, pOpen = Opening.open, pDepend = 122))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 3, pName = "Code Javascript", pClass = ggroupblack, pRes = "Brian", pGroup = Group.standardGroupTask, pOpen = Opening.open))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 31, pName = "Define Variables", pStart = "2016-02-25 00:00".toLDT, pEnd = "2016-03-17 00:00".toLDT, pClass = gtaskpurple, pRes = "Brian", pComp = 30, pParent = 3, pOpen = Opening.open, pCaption = "Caption 1"))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 32, pName = "Calculate Chart Size", pStart = "2016-03-15 00:00".toLDT, pEnd = "2016-03-24 00:00".toLDT, pClass = gtaskgreen, pRes = "Shlomy", pComp = 40, pParent = 3, pOpen = Opening.open))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 33, pName = "Draw Task Items", pClass = ggroupblack, pRes = "Someone", pComp = 40, pGroup = Group.combinedGroup, pParent = 3, pOpen = Opening.open))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 332, pName = "Task Label Table", pStart = "2016-03-06 00:00".toLDT, pEnd = "2016-03-09 00:00".toLDT, pClass = gtaskgreen, pRes = "Brian", pComp = 60, pParent = 33, pOpen = Opening.open))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 333, pName = "Task Scrolling Grid", pStart = "2016-03-11 00:00".toLDT, pEnd = "2016-03-20 00:00".toLDT, pClass = gtaskblue, pRes = "Brian1", pParent = 33, pOpen = Opening.open, pDepend = 332))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 34, pName = "Draw Task Bars", pClass = ggroupblack, pRes = "Anybody", pComp = 60, pGroup = Group.standardGroupTask, pParent = 3))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 341, pName = "Loop each Task", pStart = "2016-03-26 00:00".toLDT, pEnd = "2016-04-11 00:00".toLDT, pClass = gtaskred, pRes = "Brian", pComp = 60, pParent = 34, pOpen = Opening.open))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 342, pName = "Calculate Start/Stop", pStart = "2016-04-12 00:00".toLDT, pEnd = "2016-05-18 00:00".toLDT, pClass = gtaskpink, pRes = "Brian", pComp = 60, pParent = 34, pOpen = Opening.open))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 343, pName = "Draw Task Div", pStart = "2016-05-13 00:00".toLDT, pEnd = "2016-05-17 00:00".toLDT, pClass = gtaskred, pRes = "Brian", pComp = 60, pParent = 34, pOpen = Opening.open))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 344, pName = "Draw Completion Div", pStart = "2016-05-17 00:00".toLDT, pEnd = "2016-06-04 00:00".toLDT, pClass = gtaskred, pRes = "Brian", pComp = 60, pParent = 34, pOpen = Opening.open, pDepend = Seq(342, 343)))
//                                                                        g.AddTaskItem(new TaskItemExt(pID = 35, pName = "Make Updates", pStart = "2016-07-17 00:00".toLDT, pEnd = "2017-09-04 00:00".toLDT, pClass = gtaskpurple, pRes = "Brian", pComp = 30, pParent = 3, pOpen = Opening.open, pDepend = 333))
                                                                        g.Draw()
                                                                }

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
                thiz
            }
    }.toThisFunc.opt
}
