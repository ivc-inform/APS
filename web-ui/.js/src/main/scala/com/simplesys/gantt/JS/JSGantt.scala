package com.simplesys.gantt.JS

import com.simplesys.gantt.Format.Format
import com.simplesys.gantt._
import org.scalajs.dom.raw._

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.Date
import scala.scalajs.js.annotation.JSGlobal

object GanttChart extends js.Object {
    implicit def date2Opt(date: js.Date): Option[js.Date] = Some(date)
    implicit def int2DepSeq(int: Int): Seq[Depend] = Seq(Depend(int))
    implicit def int2Dep(int: Int): Depend = Depend(int)
    implicit def dep2Seq(dep: Depend): Seq[Depend] = Seq(dep)

    implicit class strOpt(str: String) {
        def toLDT: js.Date = {
            val res = new js.Date(js.Date.parse(str))
            //println(res.getTimezoneOffset())
            res
        }

        def toLDTOpt: Option[Date] = {
            if (str.isEmpty)
                None
            else {
                val res = new js.Date(js.Date.parse(str))
                println(res.getTime())
                res
            }
        }
    }

    implicit class dblOpt(double: Double) {
        def toLDT: js.Date = new js.Date(double)
    }

    implicit class longOpt(long: Long) {
        def toLDT: js.Date = new js.Date(long.toDouble)
    }

    val langRus = js.Dictionary(
        "format" → "Формат",
        "hour" → "Час",
        "day" → "День",
        "week" → "Неделя",
        "month" → "Месяц",
        "quarter" → "Квартал",
        "hours" → "Часов",
        "days" → "Дней",
        "weeks" → "Недель",
        "months" → "Месяцев",
        "quarters" → "Кварталов",
        "hr" → "Час",
        "dy" → "День",
        "wk" → "Неделя",
        "mth" → "Месяц",
        "qtr" → "Квартал",
        "hrs" → "Часов",
        "dys" → "Дней",
        "wks" → "Недель",
        "mths" → "Месяцев",
        "qtrs" → "Кварталов",
        "resource" → "Ресурс",
        "duration" → "Продолжит.",
        "comp" → "% Выполн.",
        "completion" → "Общая",
        "startdate" → "Начало",
        "enddate" → "Конец",
        "moreinfo" → "Доп. информация",
        "notes" → "Заметки",
        "january" → "Янваль",
        "february" → "Февраль",
        "march" → "Март",
        "april" → "Апрель",
        "maylong" → "Май",
        "june" → "Июнь",
        "july" → "Июль",
        "august" → "Август",
        "september" → "Сентябрь",
        "october" → "Октябрь",
        "november" → "Ноябрь",
        "december" → "Декабрь",
        "jan" → "Январь",
        "feb" → "Февраль",
        "mar" → "Март",
        "apr" → "Апрель",
        "may" → "Май",
        "jun" → "Июнь",
        "jul" → "Июль",
        "aug" → "Август",
        "sep" → "Сентябрь",
        "oct" → "Октябрь",
        "nov" → "Ноябрь",
        "dec" → "Декабрь",
        "sunday" → "Воскресение",
        "monday" → "Понедельник",
        "tuesday" → "Вторник",
        "wednesday" → "Среда",
        "thursday" → "Четверг",
        "friday" → "Пятница",
        "saturday" → "Суббота",
        "sun" → "Воскресение",
        "mon" → "Понедельник",
        "tue" → "Вторник",
        "wed" → "Среда",
        "thu" → "Четверг",
        "fri" → "Пятница",
        "sat" → "Суббота"
    )
}

@js.native
@JSGlobal("JSGantt.GanttChart")
protected class GanttChart(val pDiv: Element, val pFormat: String) extends js.Object {

    def addLang(pLang: String, pVals: js.Dictionary[String]): Unit = js.native

    def getDivId(): js.UndefOr[String] = js.native


    /**
      * Switches
      * =========
      *
      * Configuration Options
      * Switches
      * Many of the features of jsGanttImproved can be customised through the use of setter methods available on the GanttChart object returned by a call to JSGantt.GanttChart()
      *
      * The following options take a single numeric parameter; a value of 1 will enable the describe functionality, 0 will disable it
      *
      **/

    //Controls the display of tool tip boxes, defaults to 1 (enabled)
    def setUseToolTip(value: Int): Unit = js.native

    //Controls use of the fade effect when showing/hiding tool tips, defaults to 1 (enabled)
    def setUseFade(value: Int): Unit = js.native

    //Controls use of the sliding effect when changing between different task tool tips, defaults to 1 (enabled)
    def setUseMove(value: Int): Unit = js.native

    //Controls the use of row mouseover highlighting, defaults to 1 (enabled)
    def setUseRowHlt(value: Int): Unit = js.native

    //Controls whether the task list is sorted into parent task / start time order or is simply displayed in the order created, defaults to 1 (sort enabled)
    def setUseSort(value: Int): Unit = js.native

    //Controls whether the Resource column is displayed in the task list, defaults to 1 (show column)
    def setShowRes(value: Int): Unit = js.native

    //Controls whether the Task Duration column is displayed in the task list, defaults to 1 (show column)
    def setShowDur(value: Int): Unit = js.native

    //Controls whether the Percentage Complete column is displayed in the task list, defaults to 1 (show column)
    def setShowComp(value: Int): Unit = js.native

    //Controls whether the Task Start Date column is displayed in the task list, defaults to 1 (show column)
    def setShowStartDate(value: Int): Unit = js.native

    //Controls whether the Task End Date column is displayed in the task list, defaults to 1 (show column)
    def setShowEndDate(value: Int): Unit = js.native

    //Controls whether the Resource information is displayed in the task tool tip, defaults to 1 (show information)
    def setShowTaskInfoRes(value: Int): Unit = js.native

    //Controls whether the Task Duration information is displayed in the task tool tip, defaults to 1 (show information)
    def setShowTaskInfoDur(value: Int): Unit = js.native

    //Controls whether the Percentage Complete information is displayed in the task tool tip, defaults to 1 (show information)
    def setShowTaskInfoComp(value: Int): Unit = js.native

    //Controls whether the Task Start Date information is displayed in the task tool tip, defaults to 1 (show information)
    def setShowTaskInfoStartDate(value: Int): Unit = js.native

    //Controls whether the Task End Date information is displayed in the task tool tip, defaults to 1 (show information)
    def setShowTaskInfoEndDate(value: Int): Unit = js.native

    //Controls whether the More Information link is displayed in the task tool tip, defaults to 0 (do NOT show link)
    def setShowTaskInfoLink(value: Int): Unit = js.native

    //Controls whether the Additional Notes data is displayed in the task tool tip, defaults to 1 (show notes)
    def setShowTaskInfoNotes(value: Int): Unit = js.native

    //Controls whether the major heading in "Day" view displays the week end-date in the appropriate format (see below), defaults to 1 (show date)
    def setShowEndWeekDate(value: Int): Unit = js.native

    //Controls display of dependancy lines, defaults to 1 (show dependencies)
    def setShowDeps(value: Int): Unit = js.native


    /**
      * Key Values
      * ===========
      * The following options enable functionality using a set of specific key values
      *
      */


    /**
      * Controls where the format selector is displayed, accepts multiple parameters.
      * Valid parameter values are "Top", "Bottom".
      * Defaults to "Top".
      */

    def setShowSelector(value: String): Unit = js.native

    /**
      * Controls which format options are shown in the format selector, accepts multiple parameters.
      * Valid parameter values are "Hour", "Day", "Week", "Month", "Quarter".
      * Defaults to all valid values.
      **/

    def setFormatArr(value: String*): Unit = js.native

    /**
      * Controls which task field to use as a caption on the Gantt Chart task bar, accepts a single parameter.
      * Valid parameter values are "None", "Caption", "Resource", "Duration", "Complete".
      * Defaults to "None"
      *
      **/
    def setCaptionType(value: String): Unit = js.native

    /**
      * Valid parameter values are "yyyy-mm-dd", "dd/mm/yyyy", "mm/dd/yyyy".
      * Defaults to "yyyy-mm-dd"
      **/
    def setDateInputFormat(value: String): Unit = js.native

    /**
      * Sets the date the Gantt Chart will be scrolled to, specified in the date input format set by setDateInputFormat() above. Also accepts the special value "today"
      * Defaults to minimum display date
      **/

    def setScrollTo(value: String): Unit = js.native

    /**
      * Sets the threshold total number of cells at which the task list will use a single table cell for each row rather than one cell per period. Useful to improve performance on large charts.
      * A value of 0 disables this functionality (always use multiple cells), defaults to 25000
      **/
    def setUseSingleCell(value: Int): Unit = js.native

    /**
      * Sets translation to use when drawing the chart. Defaults to "en" as this is the only language provided in the base installation (see internationalization below for details on how to add more translations.)
      **/
    def setLang(value: String): Unit = js.native

    /**
      * Layout
      * =======
      *
      * Most of the look and feel of the Gantt Chart can be controlled using CSS however, as the length of a task bar is determined by column width, the following methods take a single numeric parameter that defines the appropriate column width in pixels.
      *
      * Note that the task bar sizing code assumes the use of collapsed table borders 1px wide.
      **/

    //Width of Gantt Chart columns in pixels when drawn in "Hour" format. Defaults to 18.
    def setHourColWidth(value: Int): Unit = js.native

    //Width of Gantt Chart columns in pixels when drawn in "Day" format. Defaults to 18.
    def setDayColWidth(value: Int): Unit = js.native

    //Width of Gantt Chart columns in pixels when drawn in "Week" format. Defaults to 36.
    def setWeekColWidth(value: Int): Unit = js.native

    //Width of Gantt Chart columns in pixels when drawn in "Month" format. Defaults to 36.
    def setMonthColWidth(value: Int): Unit = js.native

    //Width of Gantt Chart columns in pixels when drawn in "Quarter" format, although not mandatory it is recommended that this be set to a value divisible by 3. Defaults to 18.
    def setQuarterColWidth(value: Int): Unit = js.native

    //Height of Gantt Chart rows in pixels. Used to route dependency lines near end points. Defaults to 20.
    def setRowHeight(value: Int): Unit = js.native

    //Group tasks have their task bars embellished with end points, this value specifies the width of one of these end points in pixels. A short task bar"s length will be rounded up to display either a single or both endpoints correctly. Defaults to 8.
    def setMinGpLen(value: Int): Unit = js.native

    /**
      * Display Date Formats
      * ====================
      * Date display formats can be individually controlled. The methods used to set these display formats each take a single format string parameter. The format string can be made up of the following components (case sensitive)
      *
      * h:	Hour (1-12)
      * hh:	Hour (01-12)
      * pm:	am/pm indicator
      * PM:	AM/PM indicator
      * H:	Hour (0-23)
      * HH:	Hour (01-23)
      * mi:	Minutes (1-59)
      * MI:	Minutes (01-59)
      * d:	Day (1-31)
      * dd:	Day (01-31)
      * day:	Abbreviated day of week
      * DAY:	Day of week
      * m:	Month (1-12)
      * mm:	Month (01-12)
      * mon:	Abbreviated month text
      * month:	Full month text
      * yy:	Year, excluding century
      * yyyy:	Year
      * q:	Quarter (1-4)
      * qq:	Quarter (Q1-Q4)
      * w:	ISO Week number (1-53)
      * ww:	ISO Week number (01-53)
      * week:	Full ISO Week date format
      * separated by one of the following characters: "/\-.,"<space>:
      **/

    //Date format used for start and end dates in the main task list. Defaults to "dd/mm/yyyy".
    def setDateTaskTableDisplayFormat(value: String): Unit = js.native

    //Date format used for start and end dates in task tool tips. Defaults to "dd month yyyy".
    def setDateTaskDisplayFormat(value: String): Unit = js.native

    //Date format used for Gantt Chart major date headings displayed in "Hour" format. Defaults to "day dd month yyyy".
    def setHourMajorDateDisplayFormat(value: String): Unit = js.native

    //Date format used for Gantt Chart major date headings displayed in "Day" format. Defaults to "dd/mm/yyyy".
    def setDayMajorDateDisplayFormat(value: String): Unit = js.native

    //Date format used for Gantt Chart major date headings displayed in "Day" format. Defaults to "dd/mm".
    def setWeekMinorDateDisplayFormat(value: String): Unit = js.native

    //Date format used for Gantt Chart major date headings displayed in "Week" format. Defaults to "yyyy".
    def setWeekMajorDateDisplayFormat(value: String): Unit = js.native

    //Date format used for Gantt Chart major date headings displayed in "Month" format. Defaults to "yyyy".
    def setMonthMajorDateDisplayFormat(value: String): Unit = js.native

    //Date format used for Gantt Chart major date headings displayed in "Year" format. Defaults to "yyyy".
    def setQuarterMajorDateDisplayFormat(value: String): Unit = js.native

    //Date format used for Gantt Chart minor date headings displayed in "Hour" format. Defaults to "HH".
    def setHourMinorDateDisplayFormat(value: String): Unit = js.native

    //Date format used for Gantt Chart minor date headings displayed in "Day" format. Defaults to "dd".
    def setDayMinorDateDisplayFormat(value: String): Unit = js.native

    //Date format used for Gantt Chart minor date headings displayed in "Week" format. Defaults to "dd/mm".
    def setWeekMinjorDateDisplayFormat(value: String): Unit = js.native

    //Date format used for Gantt Chart minor date headings displayed in "Month" format. Defaults to "mon".
    def setMonthMinorDateDisplayFormat(value: String): Unit = js.native

    //Date format used for Gantt Chart minor date headings displayed in "Year" format. Defaults to "qq".
    def setQuarterMinorDateDisplayFormat(value: String): Unit = js.native

    def AddTaskItem(taskItem: TaskItem): Unit = js.native

    def Draw(): Unit = js.native
}

class GanttChartExt(pDiv: Element, pFormat: Format) extends GanttChart(pDiv, pFormat.toString)
