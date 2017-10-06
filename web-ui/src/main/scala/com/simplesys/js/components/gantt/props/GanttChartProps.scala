package com.simplesys.js.components.gantt.props

import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.gantt._
import com.simplesys.option.ScOption._

import scala.scalajs.js
import scala.scalajs.js.Date

class GanttChartProps extends CanvasProps {
    type classHandler <: GanttChart

    getID1 = {
        (thiz: classHandler) ⇒
            s"${thiz.getID}_ganttChart"
    }.toThisFunc.opt

    getInnerHTML = {
        (thiz: classHandler) ⇒
            import scalatags.Text.all._
            //import scalatags.JsDom.all._
            div(
                style := "width:100%;height:100%",
                id := thiz.getID1,
                link(href := "css/jquery.ganttView.css", rel := "stylesheet", `type` := "text/css"),
                link(href := "css/jquery-ui-1.12.1.css", rel := "stylesheet", `type` := "text/css"),
                link(href := "css/reset.css", rel := "stylesheet", `type` := "text/css")
            ).render
    }.toThisFunc.opt

    draw = {
        (thiz: classHandler, args: JSUndefined[IscArray[JSAny]]) ⇒

            if (!thiz.readyToDraw())
                thiz
            else {

                thiz.Super("draw", args.getOrElse(IscArray[JSAny]()))

                new GanttView(
                    thiz.getID1,
                    new GanttChartOptions {
                        override val data = js.Array(
                            new DataStructItem {
                                override val series = js.Array(
                                    new GanttDataItem {
                                        override val name = "Задача №1"
                                        override val start = new Date(2011, 8, 1)
                                        override val end = new Date(2011, 8, 3)
                                    },
                                    new GanttDataItem {
                                        override val name = "Задача №2"
                                        override val start = new Date(2011, 8, 2)
                                        override val end = new Date(2011, 8, 5)
                                        override val color = "#f0f0f0"
                                    }, new GanttDataItem {
                                        override val name = "Задача №3"
                                        override val start = new Date(2011, 8, 1)
                                        override val end = new Date(2011, 8, 10)
                                        override val color = "#e0e0e0"
                                    }
                                )
                            }
                        )
                    }
                )//. helloWorld()
                 . build()

                thiz
            }
    }.toThisFunc.opt

    redrawOnResize = false.opt
}
