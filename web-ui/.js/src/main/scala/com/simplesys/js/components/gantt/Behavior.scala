package com.simplesys.js.components.gantt

import com.simplesys.js.components.gantt.Behavior.BehaviorCallback
import com.simplesys.js.components.gantt.DateUtils._
import io.udash.wrappers.jquery.{JQuery, _}
import io.udash.wrappers.jqueryui.JQueryUi._
import io.udash.wrappers.jqueryui.{DraggableEventUIParams, DraggableOptions, ResizableOptions, ResizableUIParams}
import org.scalajs.dom.Element
import org.scalajs.dom.raw.Event

import scala.scalajs.js
import scala.scalajs.js.UndefOr._
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.{Date, UndefOr}

object Behavior {
    type BehaviorCallback = js.Function1[UndefOr[js.Any], _]
}

class Behavior(div: JQuery, opts: GanttChartOptions) extends js.Object {
    @JSName("apply")
    def apply(): Unit = {
        
        opts.behavior.foreach {
            behavior ⇒
                if (behavior.clickable.getOrElse(false))
                    bindBlockClick(div, behavior.onClick)
                
                if (behavior.resizable.getOrElse(false))
                    bindBlockResize(div, opts.cellWidth.get, opts.start.get, behavior.onResize)

                if (behavior.draggable.getOrElse(false))
                    bindBlockDrag(div, opts.cellWidth.get, opts.start.get, behavior.onDrag)
        }
    }

    def bindBlockClick(div: JQuery, callback: js.UndefOr[BehaviorCallback] = js.undefined): Unit = {
        jQuery("div.ganttview-block", div).on("click",
            (element: Element, event: JQueryEvent) ⇒ {
                callback.foreach(callback ⇒ callback(jQuery(element).data("block-data")))
            }
        )
    }

    def bindBlockResize(div: JQuery, cellWidth: Int, startDate: Date, callback: js.UndefOr[BehaviorCallback] = js.undefined): Unit = {
        jQuery("div.ganttview-block", div).resizable(
            new ResizableOptions {
                override val grid = cellWidth.asInstanceOf[js.Any]
                override val handles = "e,w".asInstanceOf[js.Any]
                override val stop = any2undefOrA({
                    (thiz: js.Any, event: Event, ui: ResizableUIParams) ⇒ {
                        val block = jQuery(thiz)
                        updateDataAndPosition(div, block, cellWidth, startDate)
                        callback.foreach(callback ⇒ callback(block.data("block-data")))
                    }
                })
            }
        )
    }

    def bindBlockDrag(div: JQuery, cellWidth: Double, startDate: Date, callback: js.UndefOr[BehaviorCallback] = js.undefined): Unit = {
        jQuery("div.ganttview-block", div).draggable(
            new DraggableOptions {
                override val axis = "x"
                override val grid = js.Array(cellWidth, cellWidth)
                override val stop = any2undefOrA({
                    (thiz: js.Any, event: Event, ui: DraggableEventUIParams) ⇒ {
                        val block = jQuery(thiz)
                        updateDataAndPosition(div, block, cellWidth, startDate)
                        callback.foreach(callback ⇒ callback(block.data("block-data")))
                    }
                })
            }
        )
    }

    def updateDataAndPosition(div: JQuery, block: JQuery, cellWidth: Double, startDate: Date): Unit = {

        val container = jQuery("div.ganttview-slide-container", div)
        val scroll = container.scrollLeft()
        val offset = block.offset().left - container.offset().left - 1 + scroll

        // Set new start date
        val daysFromStart = math.round(offset / cellWidth)
        val newStart = startDate.dclone().addDays(daysFromStart.toInt)
        block.data("block-data").foreach(_.asInstanceOf[js.Dynamic].start = newStart)

        // Set new end date
        val width = block.outerWidth()
        val numberOfDays = Math.round(width / cellWidth) - 1
        block.data("block-data").foreach(_.asInstanceOf[js.Dynamic].end = newStart.dclone().addDays(numberOfDays.toInt))

        jQuery("div.ganttview-block-text", block).text((numberOfDays + 1).toDouble)

        // Remove top and left properties to avoid incorrect block positioning,
        // set position to relative to keep blocks relative to scrollbar when scrolling
        block.css("top", "").css("left", "").css("position", "relative").css("margin-left", offset + "px")
    }
}
