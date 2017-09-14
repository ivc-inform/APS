package com.simplesys.js.components.gantt.props

import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.gantt.GanttChart
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}
import org.scalajs.jquery._

class GanttChartProps extends CanvasProps {
    type classHandler <: GanttChart

    var data: ScOption[JSObject] = ScNone

    getID1 = {
        (thiz: classHandler) ⇒
            s"${thiz.getID}_ganttChart"
    }.toThisFunc.opt

    getInnerHTML = {
        (thiz: classHandler) ⇒
            "<div style=\"width:100%;height:100%\"" + " " + s"id=${thiz.getID1}></div>"
    }.toThisFunc.opt

    draw = {
        (thiz: classHandler, args: JSUndefined[IscArray[JSAny]]) ⇒
            if (!thiz.readyToDraw())
                thiz
            else {
                thiz.Super("draw", args.get)
                jQuery(thiz.getID1).html("Hello world!")
                thiz
            }
    }.toThisFunc.opt
}
