package com.simplesys.js.components.gantt

import java.time.{LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter

import scala.scalajs.js

object DateUtils extends js.Object {
    implicit class StrOpts(str: String) {
        private val formatterDateTime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
        private val formatterDate = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        def toDate: LocalDate = LocalDate.parse(str, formatterDate)
        def toDateTime: LocalDateTime = LocalDateTime.parse(str, formatterDateTime)
    }
}

class DateUtils extends js.Object {

}
