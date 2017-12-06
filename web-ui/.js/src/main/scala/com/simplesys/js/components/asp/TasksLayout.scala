package com.simplesys.js.components.asp

import com.simplesys.SmartClient.Layout.{AbstractHLayoutSSCompanion, ChainMasterDetail}

import scala.scalajs.js

@js.native
trait TasksLayout extends ChainMasterDetail {
    var tasks: Tasks
}

@js.native
abstract trait AbstractTasksLayoutCompanion extends AbstractHLayoutSSCompanion {
}

