package io.udash.wrappers.jqueryui

import scala.scalajs.js

@js.native
trait TooltipOptions extends TooltipEvents {
  var content: js.Any = js.native
  // () or string
  var disabled: Boolean = js.native
  var hide: js.Any = js.native
  // boolean, number, string or object
  var items: String = js.native
  var position: js.Any = js.native
  // TODO
  var show: js.Any = js.native
  // boolean, number, string or object
  var tooltipClass: String = js.native
  var track: Boolean = js.native
}
