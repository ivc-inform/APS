package com.simplesys.js.components.io.udash.wrappers.jqueryui

import scala.scalajs.js
import scala.scalajs.js.Date

@js.native
trait Datepicker extends Widget with DatepickerOptions {
  var regional: js.Dictionary[js.Any] = js.native
  def setDefaults(defaults: DatepickerOptions): Unit = js.native
  def formatDate(format: String, date: Date, settings: DatepickerFormatDateOptions = js.native): String = js.native
  def parseDate(format: String, date: String, settings: DatepickerFormatDateOptions = js.native): Date = js.native
  def iso8601Week(date: Date): Double = js.native
  def noWeekends(date: Date): js.Array[js.Any] = js.native
}
