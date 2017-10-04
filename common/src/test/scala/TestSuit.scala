import java.time.{LocalDate, LocalDateTime}

import org.scalatest.FunSuite

class TestSuit extends FunSuite {
    test("rec OrdDoc") {
        import java.time.format.DateTimeFormatter
        val str = "1986-04-08 12:30:01"
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.parse(str, formatter)
        println(dateTime)

        val date = LocalDate.parse(str, formatter)
        println(date)
    }
}
