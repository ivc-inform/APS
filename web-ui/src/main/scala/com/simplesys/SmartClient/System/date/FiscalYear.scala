package com.simplesys.SmartClient.System.date

import com.simplesys.System.JSObject

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal
object FiscalYear extends JSObject{
  type FiscalYear = FiscalYear.type

  var date: Int = js.native
  var month: Int = js.native
  var year: Int = js.native
  var fiscalYear: Int = js.native
}
