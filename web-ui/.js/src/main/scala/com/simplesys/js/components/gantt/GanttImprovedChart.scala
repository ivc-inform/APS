package com.simplesys.js.components.gantt

import com.simplesys.SmartClient.Foundation.{AbstractCanvasCompanion, Canvas}

import scala.scalajs.js

@js.native
trait GanttImprovedChart extends Canvas {
    var idResult: js.UndefOr[Double]
}

@js.native
abstract trait AbstractGanttImprovedChartCompanion extends AbstractCanvasCompanion {
}

