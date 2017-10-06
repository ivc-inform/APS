package io.udash.wrappers.jqueryui

import scala.scalajs.js

@js.native
trait SpinnerEvents extends js.Object {
  var change: SpinnerEvent[js.Any] = js.native
  var create: SpinnerEvent[js.Any] = js.native
  var spin: SpinnerEvent[SpinnerUIParam] = js.native
  var start: SpinnerEvent[js.Any] = js.native
  var stop: SpinnerEvent[js.Any] = js.native
}
