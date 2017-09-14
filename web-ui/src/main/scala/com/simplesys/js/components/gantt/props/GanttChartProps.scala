package com.simplesys.js.components.gantt.props

import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.gantt.GanttChart
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._
import org.scalajs.jquery

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js._

class GanttChartProps extends CanvasProps {
    type classHandler <: GanttChart

    var data: ScOption[JSObject] = ScNone

    getID1 = {
        (thiz: classHandler) ⇒
            s"${thiz.Super("getID")}_ganttChart"
    }.toThisFunc.opt

    getInnerHTML = {
        (thiz: classHandler) ⇒
            s"<div id=${thiz.getID1()}></div>"
    }.toThisFunc.opt

    draw = {
        (thiz: classHandler, args: JSUndefined[IscArray[JSAny]]) ⇒
            if (!thiz.readyToDraw())
                thiz
            else {
                thiz.Super("draw", args.get)
                jquery(thiz.getID1()).html("Hello world!")
                thiz
            }
    }.toThisFunc.opt
}
