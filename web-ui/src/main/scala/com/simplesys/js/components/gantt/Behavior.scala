package com.simplesys.js.components.gantt

import com.simplesys.SmartClient.System.isc
import com.simplesys.System.Types.Callback
import com.simplesys.js.components.gantt.Behavior.BehaviorCallback
import com.simplesys.js.components.gantt.DateUtils._
import io.udash.wrappers.jquery.{JQuery, _}
import io.udash.wrappers.jqueryui.JQueryUi._
import io.udash.wrappers.jqueryui.ResizableOptions
import org.scalajs.dom.Element

import scala.scalajs.js
import scala.scalajs.js.Date

object Behavior {
    type BehaviorCallback = js.Function1[Option[js.Any], _]
}

class Behavior(div: JQuery, opts: GanttChartOptions) extends js.Object {
    def bindBlockClick(div: JQuery, callback: js.UndefOr[BehaviorCallback] = js.undefined): Unit = {
        jQuery("div.ganttview-block", div).on("click",
            (element: Element, event: JQueryEvent) ⇒ {
                callback.foreach(callback ⇒ callback(jQuery(element).data("block-data")))
            }
        )
    }

    def bindBlockResize(div: JQuery, cellWidth: Int, startDate: Date, callback: js.UndefOr[Callback] = js.undefined): Unit = {
        jQuery("div.ganttview-block", div).resizable(
            new ResizableOptions {

            }
        )
    }

    def updateDataAndPosition(div: JQuery, block: JQuery, cellWidth: Int, startDate: Date): Unit = {

        val container = jQuery("div.ganttview-slide-container", div)
        val scroll = container.scrollLeft()
        val offset = block.offset().left - container.offset().left - 1 + scroll

        // Set new start date
        val daysFromStart = math.round(offset / cellWidth)
        val newStart = isc.clone(startDate).addDays(daysFromStart.toInt)
        block.data("block-data").foreach(_.asInstanceOf[js.Dynamic].start = newStart)

        // Set new end date
        val width = block.outerWidth()
        val numberOfDays = Math.round(width / cellWidth) - 1
        block.data("block-data").foreach(_.asInstanceOf[js.Dynamic].end = isc.clone(newStart).addDays(numberOfDays.toInt))

        jQuery("div.ganttview-block-text", block).text((numberOfDays + 1).toDouble)

        // Remove top and left properties to avoid incorrect block positioning,
        // set position to relative to keep blocks relative to scrollbar when scrolling
        block.css("top", "").css("left", "").css("position", "relative").css("margin-left", offset + "px")
    }
}
