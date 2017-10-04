package com.simplesys.js.components.gantt

import java.time.LocalDateTime

import com.simplesys.System.Types.Color

import scala.scalajs.js

trait GanttData extends js.Object {
    val name: String
    val start: LocalDateTime
    val end: LocalDateTime
    val color: js.UndefOr[Color] = js.undefined
}

trait UnhierarchicalGanttData extends js.Object {
    val series: Seq[GanttData]
}
