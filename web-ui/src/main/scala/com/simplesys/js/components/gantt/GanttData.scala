package com.simplesys.js.components.gantt

import com.simplesys.System.Types.Color

import scala.scalajs.js
import scala.scalajs.js.Date

trait DataStructItem extends js.Object

trait UnhierarchicalGanttDataItem extends js.Object {
    val name: String
    val start: Date
    val end: Date
    val color: js.UndefOr[Color] = js.undefined
}

trait UnhierarchicalGanttDataStructItem extends DataStructItem {
    val series: Seq[UnhierarchicalGanttDataItem]
}

