package com.simplesys.js.components.gantt

import java.time.LocalDateTime

import scala.scalajs.js

trait GanttChartBehavior extends js.Object {
    val clickable: js.UndefOr[Boolean] = js.undefined
    val	draggable: js.UndefOr[Boolean] = js.undefined
    val	resizable: js.UndefOr[Boolean] = js.undefined
    val	onClick: js.UndefOr[js.Function] = js.undefined
    val	onDrag: js.UndefOr[js.Function] = js.undefined
    val	onResize: js.UndefOr[js.Function] = js.undefined
}

trait GanttChartOptions extends js.Object {
    val showWeekends: js.UndefOr[Boolean] = js.undefined
    val showToday: js.UndefOr[Boolean] = js.undefined
    var data: js.UndefOr[js.Object] = js.undefined
    val cellWidth: js.UndefOr[Int] = js.undefined
    val cellHeight: js.UndefOr[Int] = js.undefined
    val slideWidth: js.UndefOr[Int] = js.undefined
    val vHeaderWidth: js.UndefOr[Int] = js.undefined
    val dataUrl: js.UndefOr[String] = js.undefined
    val behavior: js.UndefOr[GanttChartBehavior] = js.undefined
}
