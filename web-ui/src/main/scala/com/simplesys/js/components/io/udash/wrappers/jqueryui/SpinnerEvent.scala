package io.udash.wrappers.jqueryui

import scala.scalajs.js
import org.scalajs.dom.raw._

@js.native
trait SpinnerEvent[T] extends js.Object {
  def apply(event: Event, ui: T): Unit = js.native
}
