package io.udash.wrappers.jqueryui

import scala.scalajs.js
import org.scalajs.dom.raw._
import scala.scalajs.js.|

@js.native
trait SortableOptions extends SortableEvents {
  var appendTo: js.Any = js.native
  // jQuery, Element, Selector or string
  var axis: String = js.native
  var cancel: js.Any = js.native
  // Selector
  var connectWith: js.Any = js.native
  // Selector
  var containment: js.Any = js.native
  // Element, Selector or string
  var cursor: String = js.native
  var cursorAt: js.Any = js.native
  var delay: Double = js.native
  var disabled: Boolean = js.native
  var distance: Double = js.native
  var dropOnEmpty: Boolean = js.native
  var forceHelperSize: Boolean = js.native
  var forcePlaceholderSize: Boolean = js.native
  var grid: js.Array[Double] = js.native
  var helper: String | js.Function2[Event, Sortable, Element] = js.native
  var handle: js.Any = js.native
  // Selector or Element
  var items: js.Any = js.native
  // Selector
  var opacity: Double = js.native
  var placeholder: String = js.native
  var revert: js.Any = js.native
  // boolean or number
  var scroll: Boolean = js.native
  var scrollSensitivity: Double = js.native
  var scrollSpeed: Double = js.native
  var tolerance: String = js.native
  var zIndex: Double = js.native
}
