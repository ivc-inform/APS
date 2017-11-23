package com.simplesys.SmartClient.System

import com.simplesys.SmartClient.System.RelativeDate._
import com.simplesys.SmartClient.System.date._
import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.RelativeDateRangePosition._
import com.simplesys.System.Types.RelativeDateShortcut.RelativeDateShortcut
import com.simplesys.System.Types._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal
import scala.scalajs.js.|

@js.native
trait DateUtil extends Class

@js.native
trait  AbstractDateUtilCompanion extends AbstractClassCompanion {
    def createLogicalDate(year: Int, month: Int, date: Int): Date = js.native
    def format(date: Date, format: String): String = js.native
    def getAbsoluteDate(relativeDate: RelativeDate | RelativeDateShortcut | RelativeDateString, baseDate: Date = js.native, rangePosition: RelativeDateRangePosition = js.native, isLogicalDate: Boolean = js.native): Date = js.native
    def getDisplayDay(datetime: Date): Int = js.native
    def getDisplayHours(datetime: Date): Int = js.native
    def getDisplayMinutes(datetime: Date): Int = js.native
    def getDisplayMonth(datetime: Date): Int = js.native
    def getDisplayYear(datetime: Date): Int = js.native
    def getEndOf(date: Date, period: String, logicalDate: Boolean = js.native, firstDayOfWeek: Int = js.native): Date = js.native
    def getStartOf(date: Date, period: String, logicalDate: Boolean = js.native, firstDayOfWeek: Int = js.native): Date = js.native
    def mapRelativeDateShortcut(relativeDate: RelativeDateShortcut, rangePosition: RelativeDateRangePosition = js.native): RelativeDateString = js.native
    def setNormalDisplayFormat (format: String | DateDisplayFormat| js.Function0[String]):void = js.native
    def setShortDatetimeDisplayFormat (format: String | DateDisplayFormat| js.Function0[String]):void = js.native
}

@js.native
@JSGlobal
object DateUtil extends AbstractDateUtilCompanion
