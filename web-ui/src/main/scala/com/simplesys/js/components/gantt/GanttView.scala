package com.simplesys.js.components.gantt

import com.simplesys.SmartClient.System.isc
import io.udash.wrappers.jquery.jQuery

import scala.scalajs.js

class GanttView(id: String, options: js.UndefOr[GanttChartOptions]) extends js.Object {
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

    var _data = opts.data

    if (_data.isEmpty)
        opts.dataUrl.foreach(jQuery.getJSON(_, (data: js.Array[_ <: DataStructItem], _, _) ⇒ _data = data))

    if (_data.isDefined)
        build()


    def build(): Unit = {
        val minDays = math.floor((opts.slideWidth.get / opts.cellWidth.get) + 5)
        val startEnd = DateUtils.getBoundaryDatesFromData(_data.get, minDays.toInt)

        opts.start = startEnd.minStart
        opts.end = startEnd.maxEnd

        //jQ(s"#${thiz.getID1}").html(h1("Hello World !!!").render)
        val divChart = jQuery(s"#$id")

        val div = jQuery("<div>", new js.Object {
            val `class` = "ganttview"
        })

        new Chart(div, opts).render()
        isc debugTrap divChart.html()
        divChart append div
        isc debugTrap divChart.html()

        val w = jQuery("div.ganttview-vtheader", divChart).outerWidth() + jQuery("div.ganttview-slide-container", divChart).outerWidth()
        divChart.css("width", (w + 2) + "px")

    }
}
