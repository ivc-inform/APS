package com.simplesys.js.components.gantt.props


import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.System.{IscArray, isc}
import com.simplesys.System.{JSAny, JSUndefined}
import com.simplesys.System.Types.HTMLString
import com.simplesys.function._
import com.simplesys.gantt.JS.{GanttChart, GanttChartExt, TaskItemExt}
import com.simplesys.gantt._
import com.simplesys.gantt.TaskCssClass._
import com.simplesys.js.components.gantt.GanttImprovedChart
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}
import org.scalajs.dom

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.ThisFunction0
import scalatags.Text.all._


class GanttImprovedChartProps extends CanvasProps {
    type classHandler <: GanttImprovedChart

    height = "100%"
    getID1 = {
        (thiz: classHandler) ⇒
            s"${thiz.getID}_ganttChartImproved"
    }.toThisFunc.opt

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
                isc info "draw"
                thiz
            }
    }.toThisFunc.opt

    /*var getGanttData : ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) ⇒
    }.toThisFunc.opt*/

    /*case 3 ⇒
                                                                    thizTop.record.foreach {
                                                                        record ⇒
                                                                            RPCManagerSS.sendRequest(
                                                                                RPCRequest(
                                                                                    new RPCRequestProps {
                                                                                        actionURL = "logic/arx_attatch/StopUpload".opt
                                                                                        data = js.Dictionary("id" → record.id, "status" → 0).opt
                                                                                        timeout = 60000.opt
                                                                                        sendNoQueue = true.opt
                                                                                        callback = {
                                                                                            (resp: RPCResponse, data: JSObject, req: RPCRequest) ⇒
                                                                                                if (resp.httpResponseCode == 200) {
                                                                                                    thizTop setSrc Common.attach
                                                                                                    thizTop.progressBar.foreach{
                                                                                                        progressBar ⇒
                                                                                                            progressBar setTitle ""
                                                                                                            progressBar setPercentDone 0
                                                                                                    }
                                                                                                    thizTop.record.asInstanceOf[JSDynamic].updateDynamic("status")(0)
                                                                                                }

                                                                                        }.toFunc.opt
                                                                                    }
                                                                                )
                                                                            )
                                                                    }*/
}
