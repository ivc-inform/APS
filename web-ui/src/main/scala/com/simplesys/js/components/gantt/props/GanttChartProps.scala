package com.simplesys.js.components.gantt.props

import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.gantt.GanttChart
import com.simplesys.option.ScOption._

import scala.scalajs.js._

class GanttChartProps extends CanvasProps {
    type classHandler <: GanttChart

    getInnerHTML = {
        (thiz: classHandler) ⇒
            s"<div id=${thiz.getID()}_ganttChart></div>"
    }.toThisFunc.opt

    draw = {
        (thiz: classHandler, args: JSUndefined[IscArray[JSAny]]) ⇒
            if (!thiz.readyToDraw())
                thiz
            else {
                thiz.Super("draw", args.get)
                
                thiz
            }
    }.toThisFunc.opt
}
