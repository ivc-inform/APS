package com.simplesys.js.components.gantt.props

import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.gantt._
import com.simplesys.option.DoubleType._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

import scala.scalajs.js
import scala.scalajs.js.UndefOr.any2undefOrA
import scala.scalajs.js.{Date, UndefOr}
import scalatags.Text.all._
import scalatags.Text.tags2

class GanttChartProps extends CanvasProps {
    type classHandler <: GanttChart

    height = "100%"
    getID1 = {
        (thiz: classHandler) ⇒
            s"${thiz.getID}_ganttChart"
    }.toThisFunc.opt

    var data: ScOption[js.Array[_ <: DataStructItem]] = ScNone

    getInnerHTML = {
        (thiz: classHandler) ⇒
            div(
                style := "width:100%;height:100%",
                id := thiz.getID1,
                link(href := "css/jquery-ui-1.12.1.css", rel := "stylesheet", `type` := "text/css"),
                link(href := "css/jquery.ganttView.css", rel := "stylesheet", `type` := "text/css"),
                tags2.style("body {font-family: tahoma, verdana, helvetica; font-size: 0.8em; padding: 10px;}")
            ).render
    }.toThisFunc.opt

    draw = {
        (thiz: classHandler, args: JSUndefined[IscArray[JSAny]]) ⇒
            if (!thiz.readyToDraw())
                thiz
            else {
                thiz.Super("draw")

                new GanttView(
                    thiz.getID1,
                    new GanttChartOptions {

                        override val behavior = new GanttChartBehavior {
                            override val onClick = any2undefOrA({
                                (data: UndefOr[js.Any]) ⇒ {
                                    data.foreach {
                                        data ⇒
                                            val _data = data.asInstanceOf[GanttDataItem]
                                            isc.info("You clicked on an event: { start: " + _data.start.toUTCString() + ", end: " + _data.end.toUTCString() + " }")
                                    }
                                }
                            })
                            override val onDrag = any2undefOrA({
                                (data: UndefOr[js.Any]) ⇒ {
                                    data.foreach {
                                        data ⇒
                                            val _data = data.asInstanceOf[GanttDataItem]
                                            isc.info("You dragged an event: { start: " + _data.start.toUTCString() + ", end: " + _data.end.toUTCString() + " }")
                                    }
                                }
                            })
                            override val onResize = any2undefOrA({
                                (data: UndefOr[js.Any]) ⇒ {
                                    data.foreach {
                                        data ⇒
                                            val _data = data.asInstanceOf[GanttDataItem]
                                            isc.info("You resized an event: { start: " + _data.start.toUTCString() + ", end: " + _data.end.toUTCString() + " }")
                                    }
                                }
                            })
                        }
                        override val canvasWidth = thiz.getWidth()
                        override val data = thiz.data
                    }
                )
                  .build()

                thiz
            }
    }.toThisFunc.opt

    redrawOnResize = false.opt
}
