package com.simplesys.js.components.gantt

import io.udash.wrappers.jquery.jQuery

import scala.scalajs.js.UndefOr.any2undefOrA
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.scalajs.js.{Date, UndefOr}

//import scalatags.JsDom.all._

import scala.scalajs.js

object GanttView {
    @JSExportTopLevel("GetTestDiagramGantt")
    def GetTestDiagramGantt(): Unit = {
        new GanttView(
            "ganttChart",
            new GanttChartOptions {

                override val behavior = new GanttChartBehavior {
                    override val onClick = any2undefOrA({
                        (data: UndefOr[js.Any]) ⇒ {
                            data.foreach {
                                data ⇒
                                    val _data = data.asInstanceOf[GanttDataItem]
                                    val message = "You clicked on an event: { start: " + _data.start.toUTCString() + ", end: " + _data.end.toUTCString() + " }"
                                    jQuery("#eventMessage").text(message)
                            }
                        }
                    })
                    override val onDrag = any2undefOrA({
                        (data: UndefOr[js.Any]) ⇒ {
                            data.foreach {
                                data ⇒
                                    val _data = data.asInstanceOf[GanttDataItem]
                                    val message = "You dragged an event: { start: " + _data.start.toUTCString() + ", end: " + _data.end.toUTCString() + " }"
                                    jQuery("#eventMessage").text(message)
                            }
                        }
                    })
                    override val onResize = any2undefOrA({
                        (data: UndefOr[js.Any]) ⇒ {
                            data.foreach {
                                data ⇒
                                    val _data = data.asInstanceOf[GanttDataItem]
                                    val message = "You resized an event: { start: " + _data.start.toUTCString() + ", end: " + _data.end.toUTCString() + " }"
                                    jQuery("#eventMessage").text(message)
                            }
                        }
                    })
                }
                override val slideWidth = 1000
                override val data = js.Array(
                    new DataStructItem {
                        override val series = js.Array(
                            new GanttDataItem {
                                override val name = "Задача №1"
                                override val start = new Date(2011, 8, 1)
                                override val end = new Date(2011, 8, 3)
                            } /*,
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
                                            }*/
                        )
                    }
                )
            }
        ).build()
    }
}

class GanttView(ID: String, options: js.UndefOr[GanttChartOptions]) extends js.Object {
    val defaults = new GanttChartOptions {
        override val showWeekends = true
        override val showToday = true
        override val cellWidth = 21
        override val cellHeight = 31
        override val slideWidth = 400
        override val vHeaderWidth = 100
        override val behavior = new GanttChartBehavior {
            override val clickable = true
            override val draggable = true
            override val resizable = true
        }
    }

    def build(): Unit = {

        val divChart = jQuery(s"#$ID")

        val opts: GanttChartOptions = jQuery.extend(true, defaults, options)

        var _data = opts.data

        if (_data.isEmpty)
            opts.dataUrl.foreach(jQuery.getJSON(_, (data: js.Array[_ <: DataStructItem], _, _) ⇒ _data = data))

        if (_data.isDefined) {

            val minDays = math.floor((opts.slideWidth.get / opts.cellWidth.get) + 5)
            val startEnd = DateUtils.getBoundaryDatesFromData(_data.get, minDays.toInt)

            opts.start = startEnd.minStart
            opts.end = startEnd.maxEnd

            val div = jQuery("<div>", new js.Object {
                val `class` = "ganttview"
            })

            new Chart(div, opts).render()
            divChart append div

//            println(jQuery("div.ganttview-vtheader", divChart).outerWidth())
//            println(jQuery("div.ganttview-slide-container", divChart).outerWidth())

            val w = jQuery("div.ganttview-vtheader", divChart).outerWidth() + jQuery("div.ganttview-slide-container", divChart).outerWidth()
            divChart.css("width", (w + 2) + "px")

            new Behavior(divChart, opts).apply()
        }
    }

    def helloWorld(): Unit = {
        val divChart = jQuery(s"#$ID")

        /*import scalatags.Text.all._
        val a = div(style := "width:100%;height:100%", id := "12456").render
        println(a)*/

        import scalatags.JsDom.all._
        divChart.append(h1("Hello World !").render)
    }
}
