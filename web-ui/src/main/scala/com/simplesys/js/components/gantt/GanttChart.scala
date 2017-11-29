package com.simplesys.js.components.gantt

import com.simplesys.SmartClient.Foundation.{AbstractCanvasCompanion, Canvas}
import com.simplesys.System.{JSObject, JSUndefined}
import io.udash.wrappers.jquery.JQuery

import scala.scalajs.js

@js.native
trait GanttChart extends Canvas {
    val data: js.UndefOr[js.Array[_ <: DataStructItem]]
    val canvasWidth: js.UndefOr[Int]
}

@js.native
abstract trait AbstractGanttChartCompanion extends AbstractCanvasCompanion {
}

