package com.simplesys.js.components.gantt

import com.simplesys.SmartClient.Foundation.{AbstractCanvasCompanion, Canvas}
import com.simplesys.System.{JSObject, JSUndefined}
import io.udash.wrappers.jquery.JQuery

import scala.scalajs.js

@js.native
trait GanttChart extends Canvas {
    def ganttView(option: GanttChartOptions = js.native): js.Any
}

@js.native
abstract trait AbstractGanttChartCompanion extends AbstractCanvasCompanion {
}

/*@js.native
object GanttChart extends AbstractGanttChartCompanion*/
