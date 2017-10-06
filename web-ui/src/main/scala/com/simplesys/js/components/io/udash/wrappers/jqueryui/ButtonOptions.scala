package io.udash.wrappers.jqueryui

import scala.scalajs.js
import org.scalajs.dom.raw._
import scala.scalajs.js.|

@js.native
trait ButtonOptions extends js.Object {
  var disabled: Boolean = js.native
  var icons: js.Any = js.native
  var label: String = js.native
  var text: String | Boolean = js.native
  var click: js.Function1[Event, Unit] = js.native
}
