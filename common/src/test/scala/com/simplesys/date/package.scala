package com.simplesys

import java.time.{LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter

package object date {
    implicit class StrOpts(str: String) {
        private val formatterDateTime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
        private val formatterDate = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        def toDate: LocalDate = LocalDate.parse(str, formatterDate)
        def toDateTime: LocalDateTime = LocalDateTime.parse(str, formatterDateTime)
    }
}
