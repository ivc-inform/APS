package com.simplesys.js.components.gantt.props

import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.gantt.{GanttChart, GanttChartOptions}
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}
import io.udash.wrappers.jquery._

import scala.scalajs.js.ThisFunction1
import scalatags.JsDom.all._

class GanttChartProps extends CanvasProps {
    type classHandler <: GanttChart

    var data: ScOption[JSObject] = ScNone

    getID1 = {
        (thiz: classHandler) ⇒
            s"${thiz.getID}_ganttChart"
    }.toThisFunc.opt

    getInnerHTML = {
        (thiz: classHandler) ⇒
            //jQ(div(style := "width:100%;height:100%", id := thiz.getID1).render).html()
            "<div style=\"width:100%;height:100%\"" + " " + s"id=${thiz.getID1}></div>"
    }.toThisFunc.opt

    var ganttView: ScOption[ThisFunction1[classHandler, JSUndefined[GanttChartOptions], JQuery]] = {
        (thiz: classHandler, option: JSUndefined[GanttChartOptions]) ⇒
            jQ(s"#${thiz.getID1}").html(h1("Hello World !!!").render)
    }.toThisFunc.opt


    draw = {
        (thiz: classHandler, args: JSUndefined[IscArray[JSAny]]) ⇒
            if (!thiz.readyToDraw())
                thiz
            else {
                thiz.Super("draw", args.getOrElse(IscArray[JSAny]()))

                thiz.ganttView()
                //                val text = "test 123"
                //                val dom: Div = div().render
                //                val content = span(ul(li(text))).render
                //
                //                println(jQ(dom).html())
                //                println(dom.textContent)
                //
                //                jQ(dom).html(content)
                //
                //                println(jQ(dom).html())
                //                println(jQ(dom).text())
                //                println(dom.textContent)

                //document.getElementById(thiz.getID1).innerHTML = h1("Hello world!").render.textContent
                thiz
            }
    }.toThisFunc.opt
}
