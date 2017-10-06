package io.udash.wrappers.jqueryui

import scala.scalajs.js

@js.native
trait EffectOptions extends js.Object {
  var effect: String = js.native
  var easing: String = js.native
  var duration: Double = js.native
  var complete: js.Function = js.native
}
