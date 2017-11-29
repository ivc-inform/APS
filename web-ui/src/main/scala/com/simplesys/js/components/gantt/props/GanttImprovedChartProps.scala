package com.simplesys.js.components.gantt.props


import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.RPC.props.RPCRequestProps
import com.simplesys.SmartClient.RPC.{RPCManagerSS, RPCRequest, RPCResponse}
import com.simplesys.SmartClient.System.{IscArray, RPCRequest}
import com.simplesys.System.{JSAny, JSObject, JSUndefined}
import com.simplesys.function._
import com.simplesys.js.components.gantt.GanttImprovedChart
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.request.RequestResult
import io.circe.generic.auto._
import io.circe.scalajs._
import io.circe.syntax._

import scala.language.implicitConversions
import scalatags.Text.all._


class GanttImprovedChartProps extends CanvasProps {
    type classHandler <: GanttImprovedChart

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
                //isc info s"idResult: ${thiz.idResult.getOrElse(0.0)}"

                RPCManagerSS.sendRequest(
                    RPCRequest(
                        new RPCRequestProps {
                            actionURL = "logic/getGanttData".opt
                            data = convertJsonToJs(RequestResult(idResult = thiz.idResult.getOrElse(0.0).toLong).asJson).opt
                            timeout = 60000.opt
                            sendNoQueue = true.opt
                            callback = {
                                (resp: RPCResponse, data: JSObject, req: RPCRequest) ⇒
                                    if (resp.httpResponseCode == 200) {

                                    }

                            }.toFunc.opt
                        }
                    )
                )
                thiz
            }
    }.toThisFunc.opt
}
