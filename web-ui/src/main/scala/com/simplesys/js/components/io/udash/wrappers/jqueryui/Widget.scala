package com.simplesys.js.components.io.udash.wrappers.jqueryui

import scala.scalajs.js

@js.native
trait Widget extends js.Object {
  def apply(methodName: String): JQueryUi = js.native
  def apply(options: WidgetOptions): JQueryUi = js.native
  def apply(options: AccordionOptions): JQueryUi = js.native
  def apply(optionLiteral: String, optionName: String): js.Dynamic = js.native
  def apply(optionLiteral: String, options: WidgetOptions): js.Dynamic = js.native
  def apply(optionLiteral: String, optionName: String, optionValue: js.Any): JQueryUi = js.native
  def apply(name: String, prototype: js.Any): JQueryUi = js.native
  def apply(name: String, base: js.Function, prototype: js.Any): JQueryUi = js.native
}
