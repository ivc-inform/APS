package com.simplesys.js.components.gantt.props

import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.gantt._
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._
import io.udash.wrappers.jquery._
import org.scalajs.dom.Element

import scala.scalajs.js
import scala.scalajs.js.{Date, ThisFunction1}
import scalatags.JsDom.all._

class GanttChartProps extends CanvasProps {
    type classHandler <: GanttChart

    getID1 = {
        (thiz: classHandler) ⇒
            s"${thiz.getID}_ganttChart"
    }.toThisFunc.opt

    getInnerHTML = {
        (thiz: classHandler) ⇒
            //jQ(div(style := "width:100%;height:100%", id := thiz.getID1).render).html()
            "<div style=\"width:100%;height:100%\"" + " " + s"id=${thiz.getID1}></div>"
    }.toThisFunc.opt

    var ganttView: ScOption[ThisFunction1[classHandler, js.UndefOr[GanttChartOptions], _]] = {
        (thiz: classHandler, options: js.UndefOr[GanttChartOptions]) ⇒
            val defaults = new GanttChartOptions {
                override val showWeekends = true
                override val showToday = true
                override val cellWidth = 21
                override val cellHeight = 31
                override val slideWidth = 500
                override val vHeaderWidth = 100
                override val behavior = new GanttChartBehavior {
                    override val clickable = true
                    override val draggable = true
                    override val resizable = true
                }
            }

            val opts: GanttChartOptions = jQuery.extend(true, defaults, options)
            //isc debugTrap opts

            var _data = opts.data

            if (_data.isEmpty)
                opts.dataUrl.foreach(jQuery.getJSON(_, (data: js.Array[_ <: DataStructItem], _, _) ⇒ _data = data))

            if (_data.isDefined)
                build()


            def build(): Unit = {
                val minDays = math.floor((opts.slideWidth.get / opts.cellWidth.get) + 5)
                val startEnd = DateUtils.getBoundaryDatesFromData(_data.get, minDays.toInt);

                opts.start = startEnd.minStart
                opts.end = startEnd.maxEnd

                //jQ(s"#${thiz.getID1}").html(h1("Hello World !!!").render)
                val els = jQuery(s"#${thiz.getID1}")

                els.each(
                    (element: Element, _) ⇒ {
                        val container = jQuery(element)

                        val div = jQuery("<div>", new js.Object{
                            val `class` = "ganttview"
                        })

                        //isc debugTrap div

                        new Chart(div, opts).render()
                    }
                )

            }
    }.toThisFunc.opt


    draw = {
        (thiz: classHandler, args: JSUndefined[IscArray[JSAny]]) ⇒
            if (!thiz.readyToDraw())
                thiz
            else {
                thiz.Super("draw", args.getOrElse(IscArray[JSAny]()))

                thiz.ganttView(
                    new GanttChartOptions {
                        override val data = js.Array(
                            new DataStructItem {
                                override val series = js.Array(
                                    new GanttDataItem {
                                        override val name = "Задача №1"
                                        override val start = new Date(2011, 8, 1)
                                        override val end = new Date(2012, 8, 3)
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
                )

                thiz
            }
    }.toThisFunc.opt
}
