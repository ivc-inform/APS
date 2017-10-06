package io.udash.wrappers.jqueryui

import scala.scalajs.js
import scala.scalajs.js.Date

@js.native
trait UI extends js.Object {
  def mouse(method: String): JQueryUi = js.native
  def mouse(options: MouseOptions): JQueryUi = js.native
  def mouse(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  def mouse(optionLiteral: String, optionValue: js.Any): js.Dynamic = js.native
  var accordion: Accordion = js.native
  var autocomplete: Autocomplete = js.native
  var button: Button = js.native
  var buttonset: Button = js.native
  var datepicker: Datepicker = js.native
  var dialog: Dialog = js.native
  var keyCode: KeyCode = js.native
  var menu: Menu = js.native
  var progressbar: Progressbar = js.native
  var slider: Slider = js.native
  var spinner: Spinner = js.native
  var tabs: Tabs = js.native
  var tooltip: Tooltip = js.native
  var version: String = js.native
}
