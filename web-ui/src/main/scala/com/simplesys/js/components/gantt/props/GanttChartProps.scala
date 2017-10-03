package com.simplesys.js.components.gantt.props

import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.gantt.GanttChart
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scalatags.JsDom.all._
import io.udash.wrappers.jquery._
import org.scalajs.dom
import dom.document

class GanttChartProps extends CanvasProps {
    type classHandler <: GanttChart

    var data: ScOption[JSObject] = ScNone

    getID1 = {
        (thiz: classHandler) ⇒
            s"${thiz.getID}_ganttChart"
    }.toThisFunc.opt

    getInnerHTML = {
        (thiz: classHandler) ⇒
          div(style := "width:100%;height:100%", id := thiz.getID1).render.textContent
    }.toThisFunc.opt

    /*draw = {
        (thiz: classHandler, args: JSUndefined[IscArray[JSAny]]) ⇒
            if (!thiz.readyToDraw())
                thiz
            else {
                thiz.Super("draw", args.getOrElse(IscArray[JSAny]()))
                  //jQ(thiz.getID1).html("Hello world!")
                  //document.getElementById(thiz.getID1).innerHTML = "Hello world!"
                thiz
            }
    }.toThisFunc.opt*/
}
