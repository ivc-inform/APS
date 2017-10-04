package com.simplesys.js.components.gantt

import java.time.{LocalDate, LocalDateTime}

import com.simplesys.System.Types.Color

import scala.scalajs.js

trait GanttData extends js.Object {
    val name: String
    val start: LocalDate
    val end: LocalDate
    val color: js.UndefOr[Color] = js.undefined
}

trait UnhierarchicalGanttData extends js.Object {
    val series: Seq[GanttData]
}
