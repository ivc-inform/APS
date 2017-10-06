package io.udash.wrappers.jqueryui

import scala.scalajs.js

@js.native
trait SliderOptions extends SliderEvents {
  var animate: js.Any = js.native
  // boolean, string or number
  var disabled: Boolean = js.native
  var max: Double = js.native
  var min: Double = js.native
  var orientation: String = js.native
  var range: js.Any = js.native
  // boolean or string
  var step: Double = js.native
  var value: Double = js.native
  var values: js.Array[Double] = js.native
  var highlight: Boolean = js.native
}
