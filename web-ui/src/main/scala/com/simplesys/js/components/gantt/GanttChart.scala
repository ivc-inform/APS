package com.simplesys.js.components.gantt

import com.simplesys.SmartClient.Foundation.{AbstractCanvasCompanion, Canvas}
import com.simplesys.System.{JSObject, JSUndefined}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait GanttChart extends Canvas {
    val data: JSUndefined[JSObject]
}

@js.native
abstract trait AbstractGanttChartCompanion extends AbstractCanvasCompanion {
}

/*@js.native
object GanttChart extends AbstractGanttChartCompanion*/
