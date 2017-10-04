package com.simplesys.js.components.gantt

import com.simplesys.System.Types.Color

import scala.scalajs.js
import scala.scalajs.js.Date

trait DataStructItem extends js.Object {
    val id: js.UndefOr[String] = js.undefined
    val name: js.UndefOr[String] = js.undefined
    val series: js.Array[_ <: GanttDataItem]
}

trait GanttDataItem extends js.Object {
    val name: String
    val start: Date
    val end: Date
    val color: js.UndefOr[Color] = js.undefined
}


