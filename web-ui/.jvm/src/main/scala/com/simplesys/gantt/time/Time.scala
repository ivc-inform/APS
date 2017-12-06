package com.simplesys.gantt.time


import java.time.format.{DateTimeFormatter, DateTimeFormatterBuilder}
import java.time.temporal.ChronoField._
import java.time.{LocalDateTime, ZoneId}
import java.util.Locale

object Time {
    val dash = "-"
    val space = " "

    val GANTIMPROVE_LOCAL_DATE =
        new DateTimeFormatterBuilder()
          .appendValue(YEAR)
          .appendLiteral(dash)
          .appendValue(MONTH_OF_YEAR, 2)
          .appendLiteral(dash)
          .appendValue(DAY_OF_MONTH, 2)
          .toFormatter(Locale.getDefault(Locale.Category.FORMAT))

    val GANTIMPROVE_LOCAL_TIME =
        new DateTimeFormatterBuilder()
          .appendValue(HOUR_OF_DAY, 2)
          .appendLiteral(':')
          .appendValue(MINUTE_OF_HOUR, 2)
          .toFormatter(Locale.getDefault(Locale.Category.FORMAT))

    val GANTIMPROVE_LOCAL_DATE_TIME =
        new DateTimeFormatterBuilder()
          .parseCaseInsensitive.append(GANTIMPROVE_LOCAL_DATE)
          .appendLiteral(space)
          .append(GANTIMPROVE_LOCAL_TIME)
          .toFormatter(Locale.getDefault(Locale.Category.FORMAT))

    def localDateTime2Str(localDateTime: LocalDateTime, dateTimeFormatter: DateTimeFormatter = GANTIMPROVE_LOCAL_DATE_TIME): String = localDateTime.format(dateTimeFormatter)


    implicit class LocalDateTimeOpt(localDateTime: LocalDateTime) {
        def getMillis: Long = localDateTime.atZone(ZoneId.systemDefault()).toInstant.toEpochMilli
        def asString(dateTimeFormatter: DateTimeFormatter = GANTIMPROVE_LOCAL_DATE_TIME): String = localDateTime2Str(localDateTime, dateTimeFormatter)

    }
}
