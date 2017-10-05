package com.simplesys.js.components.gantt

import io.udash.wrappers.jquery.JQuery

import scala.scalajs.js
import io.udash.wrappers.jquery._
import com.simplesys.js.common._

class Chart(div: JQuery, opts: GanttChartOptions) extends js.Object {
    def render(): Unit = {
        opts.data.foreach(data ⇒ opts.cellHeight.foreach(addVtHeader(div, data, _)))
    }

    def addVtHeader(div: JQuery, data: js.Array[_ <: DataStructItem], cellHeight: Int): Unit = {
        val headerDiv = jQuery("<div>", new js.Object {
            val `class` = "ganttview-vtheader"
        })

        data.toSeqZipWithIndex.foreach {
            case (dataStructItem, i) ⇒

                val itemDiv = jQuery("<div>", js.Object {
                    val `class` = "ganttview-vtheader-item"
                })

                dataStructItem.name.foreach {
                    name ⇒
                        if (name.trim.nonEmpty)
                            itemDiv.append(jQuery("<div>", new js.Object {
                                val `class` = "ganttview-vtheader-item-name"
                                val `css` = new js.Object {
                                    val `height` = (dataStructItem.series.length * cellHeight) + "px"
                                }
                            }).append(name))
                }

                val seriesDiv = jQuery("<div>", new js.Object {
                    val `class` = "ganttview-vtheader-series"
                })

                dataStructItem.series.toSeqZipWithIndex.foreach {
                    case (ganttDataItem, j) ⇒
                        seriesDiv.append(jQuery("<div>", new js.Object {
                            val `class` = "ganttview-vtheader-series-name"
                        }).append(ganttDataItem.name))
                }

                itemDiv append seriesDiv
                headerDiv append itemDiv
        }

        div append headerDiv
    }
}
