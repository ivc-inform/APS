package io.udash.wrappers.jqueryui

import scala.scalajs.js

@js.native
trait SelectableOptions extends SelectableEvents {
  var autoRefresh: Boolean = js.native
  var cancel: String = js.native
  var delay: Double = js.native
  var disabled: Boolean = js.native
  var distance: Double = js.native
  var filter: String = js.native
  var tolerance: String = js.native
}
