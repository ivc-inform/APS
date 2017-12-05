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

                                                                        seq.foreach (g AddTaskItem _)
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
