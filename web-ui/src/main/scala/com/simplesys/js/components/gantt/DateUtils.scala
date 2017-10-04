package com.simplesys.js.components.gantt

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

        new BoundaryDatesFromData {
            override val maxEnd = _maxEnd
            override val minStart = _minStart
        }
    }
}
