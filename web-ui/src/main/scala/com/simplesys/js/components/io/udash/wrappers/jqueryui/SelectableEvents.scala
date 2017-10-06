package com.simplesys.js.components.io.udash.wrappers.jqueryui

import scala.scalajs.js
import org.scalajs.dom.raw._

@js.native
trait SelectableEvents extends js.Object {
  def selected(event: Event, ui: js.Any): Unit = js.native
  def selecting(event: Event, ui: js.Any): Unit = js.native
  def start(event: Event, ui: js.Any): Unit = js.native
  def stop(event: Event, ui: js.Any): Unit = js.native
  def unselected(event: Event, ui: js.Any): Unit = js.native
  def unselecting(event: Event, ui: js.Any): Unit = js.native
}
