package com.simplesys.SmartClient.Workflow

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal
import scala.scalajs.js.|

@js.native
trait Task extends ProcessElement {
}

@js.native
abstract trait AbstractTaskCompanion extends AbstractProcessElementCompanion {
}

@js.native
@JSGlobal
object Task extends AbstractTaskCompanion

