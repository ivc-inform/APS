package com.simplesys.js.components.gantt

import com.simplesys.SmartClient.System.isc
import com.simplesys.js.common._

import scala.scalajs.js
import scala.scalajs.js.Date

trait BoundaryDatesFromData extends js.Object {
    val minStart: Date
    val maxEnd: Date
}

object DateUtils extends js.Object {
    implicit class DateOpts(date: Date) {
        def addDays(days: Int): Date = {
            val res = new Date(date.valueOf)
            res.setDate(res.getDate + days)
            res
        }

        def isWeekend(): Boolean = date.getDay() % 6 == 0
    }

    def daysBetween(start: Date, end: Date): Double = math.ceil((end.getTime() - start.getTime()) / (1000 * 3600 * 24))

    def getBoundaryDatesFromData(data: js.Array[_ <: DataStructItem], minDays: Int): BoundaryDatesFromData = {
        var _minStart = new Date()
        var _maxEnd = new Date()
        
        data.toSeqZipWithIndex.foreach {
            case (dataStructItem, i) ⇒
                dataStructItem.series.toSeqZipWithIndex.foreach {
                    case (ganttDataItem, j) ⇒
                        val start = ganttDataItem.start
                        val end = ganttDataItem.end

                        if (i == 0 && j == 0) {
                            _minStart = start
                            _maxEnd = end
                        }

                        if (start.getTime() < _minStart.getTime())
                            _minStart = start

                        if (end.getTime() > _maxEnd.getTime())
                            _maxEnd = end

                }
        }

        if (daysBetween(_minStart, _maxEnd) < minDays) {
            _maxEnd = isc.clone(_minStart).addDays(minDays);
        }

        new BoundaryDatesFromData {
            override val maxEnd = _maxEnd
            override val minStart = _minStart
        }
    }
}
