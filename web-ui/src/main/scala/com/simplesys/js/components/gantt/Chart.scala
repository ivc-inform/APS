package com.simplesys.js.components.gantt

import com.simplesys.SmartClient.System.isc
import io.udash.wrappers.jquery.JQuery

import scala.scalajs.js
import io.udash.wrappers.jquery._
import com.simplesys.js.common._

import scala.scalajs.js.Date
import com.simplesys.js.components.gantt.DateUtils._

class Chart(div: JQuery, opts: GanttChartOptions) extends js.Object {
    def render(): Unit = {
        opts.data.foreach(data ⇒ opts.cellHeight.foreach(addVtHeader(div, data, _)))

        val slideDiv = {
            val _css = new js.Object {
                val width = opts.slideWidth.get + "px"
            }

            jQuery("<div>", new js.Object {
                val `class` = "ganttview-slide-container"
                val css = _css
            })
        }

        isc debugTrap slideDiv

        val dates = opts.start.map {
            start ⇒
                opts.end.map {
                    end ⇒
                        getDates(start, end)
                }
        }
    }

    def getDates(start: Date, end: Date): js.Array[js.Array[js.Array[Date]]] = {

        val dates = js.Array[js.Array[js.Array[Date]]]()
        dates.update(start.getFullYear(), js.Array[js.Array[Date]]())
        dates(start.getFullYear()).update(start.getMonth(), js.Array(start))

        //isc debugTrap dates

        var last = start
        /*while (last.getTime() < end.getTime()) {
            val next = isc.clone(last).addDays(1)
        }*/
        dates
    }

    def addVtHeader(div: JQuery, data: js.Array[_ <: DataStructItem], cellHeight: Int): Unit = {

        val headerDiv = jQuery("<div>", new js.Object {
            val `class` = "ganttview-vtheader"
        })

        //isc debugTrap headerDiv

        data.toSeqZipWithIndex.foreach {
            case (dataStructItem, i) ⇒

                val itemDiv = jQuery("<div>", new js.Object {
                    val `class` = "ganttview-vtheader-item"
                })

                //isc debugTrap itemDiv

                dataStructItem.name.foreach {
                    name ⇒
                        //isc debugTrap name
                        if (name.trim.nonEmpty) {
                            val _css = new js.Object {
                                val height = (dataStructItem.series.length * cellHeight) + "px"
                            }

                            itemDiv.append(jQuery("<div>", new js.Object {
                                val `class` = "ganttview-vtheader-item-name"
                                val css = _css
                            }).append(name))
                        }
                }

                val seriesDiv = jQuery("<div>", new js.Object {
                    val `class` = "ganttview-vtheader-series"
                })

                //isc debugTrap seriesDiv

                dataStructItem.series.toSeqZipWithIndex.foreach {
                    case (ganttDataItem, j) ⇒
                        //isc debugTrap ganttDataItem

                        seriesDiv.append(jQuery("<div>", new js.Object {
                            val `class` = "ganttview-vtheader-series-name"
                        }).append(ganttDataItem.name))
                }

                itemDiv append seriesDiv
                headerDiv append itemDiv
        }

        //isc debugTrap headerDiv
        div append headerDiv
    }
}
