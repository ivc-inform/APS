package com.simplesys.js.components.gantt

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.Date

trait BoundaryDatesFromData extends js.Object {
    val minStart: Date
    val maxEnd: Date
}

class DateUtils extends js.Object {
    def getBoundaryDatesFromData(data: js.Array[_ <: DataStructItem], minDays: Int): BoundaryDatesFromData = {
        val _minStart = new Date()
        val _maxEnd = new Date()

        val dataSeq: mutable.Seq[_ <: DataStructItem] = data
        dataSeq.zipWithIndex.foreach {
            case (dataStructItem, i) ⇒
                val series: mutable.Seq[_ <: GanttDataItem] = dataStructItem.series
                series.zipWithIndex.foreach {
                    case (ganttDataItem, j) ⇒
                }
        }

        new BoundaryDatesFromData {
            override val maxEnd = _maxEnd
            override val minStart = _minStart
        }
    }
}
