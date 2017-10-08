package com.simplesys.js.components.gantt

import com.simplesys.js.common._
import com.simplesys.js.components.gantt.DateUtils._
import io.udash.wrappers.jquery.{JQuery, _}

import scala.scalajs.js
import scala.scalajs.js.Date

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

                addHzHeader(slideDiv, dates, opts.cellWidth.get)
                addGrid(slideDiv, opts.data.get, dates, opts.cellWidth.get, opts.showWeekends.get, opts.showToday.get)
                addBlockContainers(slideDiv, opts.data.get)
                addBlocks(slideDiv, opts.data.get, opts.cellWidth.get, opts.start.get)
                div append slideDiv
                applyLastClass(div.parent())
        }
    }

    private val monthNames = js.Array("Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь")

    def getDates(start: Date, end: Date): js.Array[js.Array[js.Array[Date]]] = {

        val dates = js.Array[js.Array[js.Array[Date]]]()
        dates.update(start.getFullYear(), js.Array[js.Array[Date]]())
        dates(start.getFullYear()).update(start.getMonth(), js.Array(start))

        var last = start
        while (last.getTime() < end.getTime()) {
            val next = last.dclone().addDays(1)

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

        val _css = new js.Object {
            val width = "135px"
        }

        val headerDiv = jQuery("<div>", new js.Object {
            val `class` = "ganttview-vtheader"
            val css = _css
        })

        data.foreach {
            dataStructItem ⇒

                val itemDiv = jQuery("<div>", new js.Object {
                    val `class` = "ganttview-vtheader-item"
                })

                dataStructItem.name.foreach {
                    name ⇒
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

                dataStructItem.series.foreach {
                    ganttDataItem ⇒
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

        dates.zipWithIndex.foreach {
            case (year, y) ⇒
                if (year.isDefigned) {
                    year.zipWithIndex.foreach {
                        case (mounth, m) ⇒
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

                                mounth.foreach {
                                    date ⇒
                                        if (date.isDefigned)
                                            daysDiv.append(jQuery("<div>", new js.Object {
                                                val `class` = "ganttview-hzheader-day"
                                            }).append(date.getDate()))
                                }
                            }
                    }
                }
        }

        monthsDiv.css("width", totalW + "px")
        daysDiv.css("width", totalW + "px")
        headerDiv.append(monthsDiv).append(daysDiv)
        //isc debugTrap headerDiv.html()
        div.append(headerDiv)
    }

    def addGrid(div: JQuery, data: js.Array[_ <: DataStructItem], dates: js.Array[js.Array[js.Array[Date]]], cellWidth: Int, showWeekends: Boolean, showToday: Boolean): Unit = {
        val gridDiv = jQuery("<div>", new js.Object {
            val `class` = "ganttview-grid"
        })

        val rowDiv = jQuery("<div>", new js.Object {
            val `class` = "ganttview-grid-row"
        })

        dates.foreach {
            year ⇒
                if (year.isDefigned) {
                    year.foreach {
                        mounth ⇒
                            if (mounth.isDefigned) {
                                mounth.foreach {
                                    date ⇒
                                        if (date.isDefigned) {
                                            val cellDiv = jQuery("<div>", new js.Object {
                                                val `class` = "ganttview-grid-row-cell"
                                            })

                                            if (date.isWeekend && showWeekends)
                                                cellDiv.addClass("ganttview-weekend")

                                            if (date.isToday && showToday)
                                                cellDiv.addClass("ganttview-today")

                                            rowDiv.append(cellDiv)
                                        }

                                }
                            }
                    }
                }
        }

        val w = jQuery("div.ganttview-grid-row-cell", rowDiv).length * cellWidth
        rowDiv.css("width", w + "px")
        gridDiv.css("width", w + "px")

        data.foreach {
            dataStructItem ⇒
                dataStructItem.series.foreach {
                    _ ⇒ gridDiv.append(rowDiv.clone(false, false))

                }
        }

        //isc debugTrap gridDiv.html()
        div append gridDiv
    }

    def addBlockContainers(div: JQuery, data: js.Array[_ <: DataStructItem]): Unit = {
        val blocksDiv = jQuery("<div>", new js.Object {
            val `class` = "ganttview-blocks"
        })

        data.foreach {
            dataStructItem ⇒
                dataStructItem.series.foreach {
                    _ ⇒
                        blocksDiv.append(jQuery("<div>", new js.Object {
                            val `class` = "ganttview-block-container"
                        }));

                }
        }

        div append blocksDiv
    }

    def addBlocks(div: JQuery, data: js.Array[_ <: DataStructItem], cellWidth: Int, start: Date): Unit = {
        val rows = jQuery("div.ganttview-blocks div.ganttview-block-container", div)
        var rowIdx = 0

        data.foreach {
            dataStructItem ⇒
                dataStructItem.series.foreach {
                    ganttDataItem ⇒
                        val size = DateUtils.daysBetween(ganttDataItem.start, ganttDataItem.end) + 1
                        val offset = DateUtils.daysBetween(start, ganttDataItem.start);

                        val _css = new js.Object {
                            val width = ((size * cellWidth) - 9) + "px"
                            val `margin-left` = ((offset * cellWidth) + 3) + "px"
                        }
                        val block = jQuery("<div>", new js.Object {
                            val `class` = "ganttview-block"
                            val title = ganttDataItem.name + ", " + size + " days"
                            val css = _css
                        })

                        addBlockData(block, dataStructItem, ganttDataItem)

                        ganttDataItem.color.foreach(block.css("background-color", _))

                        block.append(jQuery("<div>", new js.Object {
                            val `class` = "ganttview-block-text"
                        }).text(size))

                        jQuery(rows.at(rowIdx)).append(block)
                        rowIdx += 1
                }
        }

        //isc debugTrap rows.html()
    }

    def addBlockData(block: JQuery, data: DataStructItem, series: GanttDataItem): Unit = {
        val blockData = new js.Object {
            val id = data.id
            val name = data.name
        }

        jQuery.extend(blockData, series)
        block.data("block-data", blockData)
    }

    def applyLastClass(div: JQuery): Unit = {
        jQuery("div.ganttview-grid-row div.ganttview-grid-row-cell:last-child", div).addClass("last")
        jQuery("div.ganttview-hzheader-days div.ganttview-hzheader-day:last-child", div).addClass("last")
        jQuery("div.ganttview-hzheader-months div.ganttview-hzheader-month:last-child", div).addClass("last")
    }
}
