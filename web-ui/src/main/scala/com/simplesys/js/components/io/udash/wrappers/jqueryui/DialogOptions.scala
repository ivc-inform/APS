package io.udash.wrappers.jqueryui

import scala.scalajs.js
import org.scalajs.dom.raw._
import scala.scalajs.js.|

@js.native
trait DialogOptions extends DialogEvents {
  var autoOpen: Boolean = js.native
  var buttons: js.Dictionary[js.Function1[Event, Unit]] | js.Array[DialogButtonOptions] = js.native
  var closeOnEscape: Boolean = js.native
  var closeText: String = js.native
  var appendTo: String = js.native
  var dialogClass: String = js.native
  var disabled: Boolean = js.native
  var draggable: Boolean = js.native
  var height: Double | String = js.native
  var hide: Boolean | Double | String | DialogShowHideOptions = js.native
  var maxHeight: Double = js.native
  var maxWidth: Double = js.native
  var minHeight: Double = js.native
  var minWidth: Double = js.native
  var modal: Boolean = js.native
  var position: js.Any = js.native
  // object, string or []
  var resizable: Boolean = js.native
  var show: Boolean | Double | String | DialogShowHideOptions = js.native
  var stack: Boolean = js.native
  var title: String = js.native
  var width: js.Any = js.native
  // number or string
  var zIndex: Double = js.native
}
