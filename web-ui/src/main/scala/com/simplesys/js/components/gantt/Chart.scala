package com.simplesys.js.components.gantt

import com.simplesys.SmartClient.System.isc
import io.udash.wrappers.jquery.JQuery

import scala.scalajs.js
import io.udash.wrappers.jquery._
import com.simplesys.js.common._

import scala.scalajs.js.{Date, UndefOr}
import com.simplesys.js.components.gantt.DateUtils._

import scala.collection.mutable

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

        opts.start.flatMap {
            start ⇒
                opts.end.map {
                    end ⇒
                        getDates(start, end)
                }
        }.foreach {
            dates ⇒
                //isc debugTrap dates
                addHzHeader(slideDiv, dates, opts.cellWidth.get)
        }


    }

    private val monthNames = js.Array("Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь")

    def getDates(start: Date, end: Date): js.Array[js.Array[js.Array[Date]]] = {

        val dates = js.Array[js.Array[js.Array[Date]]]()
        dates.update(start.getFullYear(), js.Array[js.Array[Date]]())
        dates(start.getFullYear()).update(start.getMonth(), js.Array(start))

        var last = start
        while (last.getTime() < end.getTime()) {
            val next = isc.clone(last).addDays(1)

            if (dates(next.getFullYear()).isUndefigned)
                dates.update(next.getFullYear(), js.Array())

            if (dates(next.getFullYear())(next.getMonth()).isUndefigned)
                dates(next.getFullYear()).update(next.getMonth(), js.Array())

            dates(next.getFullYear())(next.getMonth()).push(next)
            last = next
        }
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

    def addHzHeader(div: JQuery, dates: js.Array[js.Array[js.Array[Date]]], cellWidth: Int): Unit = {
        val headerDiv = jQuery("<div>", new js.Object {
            val `class` = "ganttview-hzheader"
        })
        val monthsDiv = jQuery("<div>", new js.Object {
            val `class` = "ganttview-hzheader-months"
        })
        val daysDiv = jQuery("<div>", new js.Object {
            val `class` = "ganttview-hzheader-days"
        })
        var totalW = 0

        var y = 0
        dates.toSeq.foreach {
            year ⇒
                if (year.isDefigned) {
                    var m = 0
                    year.toSeq.foreach {
                        mounth ⇒
                            if (mounth.isDefigned) {
                                var w = mounth.length * cellWidth
                                totalW += w

                                val _css = new js.Object {
                                    val width = (w - 1) + "px"
                                }

                                monthsDiv.append(jQuery("<div>", new js.Object {
                                    val `class` = "ganttview-hzheader-month"
                                    val css = _css
                                }).append(monthNames(m) + "/" + y))

                                mounth.toSeq.foreach {
                                    date ⇒
                                        if (date.isDefigned)
                                            daysDiv.append(jQuery("<div>", new js.Object {
                                                val `class` = "ganttview-hzheader-day"
                                            }).append(date.getDate()))
                                }
                            }
                            m += 1
                    }
                }
                y += 1
        }

        monthsDiv.css("width", totalW + "px")
        daysDiv.css("width", totalW + "px")
        headerDiv.append(monthsDiv).append(daysDiv)
        isc debugTrap headerDiv.html()
        div.append(headerDiv)
    }

    def addGrid(div: JQuery, data: js.Array[_ <: DataStructItem], dates: js.Array[js.Array[js.Array[Date]]], cellWidth: Int, showWeekends: Boolean, showToday: Boolean): Unit = {

    }
}
