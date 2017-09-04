package com.simplesys.SmartClient.Workflow

import com.simplesys.SmartClient.System.{AbstractClassCompanion, Class}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
trait ProcessElement extends Class {
}

@js.native
abstract trait AbstractProcessElementCompanion extends AbstractClassCompanion {
}

@js.native
@JSGlobal
object ProcessElement extends AbstractProcessElementCompanion

