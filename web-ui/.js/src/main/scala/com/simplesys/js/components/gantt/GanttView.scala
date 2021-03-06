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
        ).build()
    }
}

class GanttView(ID: String, options: js.UndefOr[GanttChartOptions]) extends js.Object {
    val defaults = new GanttChartOptions {
        override val showWeekends = true
        override val showToday = true
        override val cellWidth = 21
        override val cellHeight = 31
        override val slideWidth = 1000
        override val canvasWidth: js.UndefOr[Int] = 1600
        override val vHeaderWidth: js.UndefOr[Int] = 150
        override val behavior = new GanttChartBehavior {
            override val clickable = false
            override val draggable = false
            override val resizable = false
        }
    }

    def build(): Unit = {

        val _canvasWidth: Int = options.get.canvasWidth.getOrElse(defaults.canvasWidth.get)
        val _vHeaderWidth: Int = options.get.vHeaderWidth.getOrElse(defaults.vHeaderWidth.get)


        if (_canvasWidth > _vHeaderWidth) {
            val divChart = jQuery(s"#$ID")

            val options1 = if (options.isDefined) {
                if (options.get.canvasWidth.isDefined) {
                    new GanttChartOptions {
                        override val slideWidth = _canvasWidth - _vHeaderWidth - 10
                    }
                } else js.Object()
            } else js.Object()

            val opts: GanttChartOptions = jQuery.extend(true, defaults, options, options1)

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

                val w = jQuery("div.ganttview-vtheader", divChart).outerWidth() + jQuery("div.ganttview-slide-container", divChart).outerWidth()
                divChart.css("width", (w + 2) + "px")

                new Behavior(divChart, opts).apply()
            }
        }
    }

    def helloWorld(): Unit = {
        val divChart = jQuery(s"#$ID")

        import scalatags.JsDom.all._
        divChart.append(h1("Hello World !").render)
    }
}
