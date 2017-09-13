package com.simplesys.SmartClient.Workflow

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
trait UserTask extends Task {
}

@js.native
abstract trait AbstractUserTaskCompanion extends AbstractTaskCompanion {
}

@js.native
@JSGlobal
object UserTask extends AbstractUserTaskCompanion

