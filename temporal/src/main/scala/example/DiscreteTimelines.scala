package example

import java.time.LocalDate

import example.TemporalData.{DiscreteTimeUnit, Time}

object IntegerTimeUnit {
  private implicit def integerTimeUnit: DiscreteTimeUnit[Int] = new DiscreteTimeUnit[Int] {
    override val MIN: Int = Int.MinValue
    override val MAX: Int = Int.MaxValue

    override def compare(x: Int, y: Int): Int = x.compareTo(y)

    override def succ(x: Int): Int = x + 1

    override def pred(x: Int): Int = x - 1
  }

  object IntegerTimelines extends Time[Int]

}

object LocalDateTimeUnit {

  private implicit def localDateTimeUnit: DiscreteTimeUnit[LocalDate] = new DiscreteTimeUnit[LocalDate] {
    override val MIN: LocalDate = LocalDate.MIN
    override val MAX: LocalDate = LocalDate.MAX

    override def compare(x: LocalDate, y: LocalDate): Int = x.compareTo(y)

    override def succ(x: LocalDate): LocalDate = x.plusDays(1)

    override def pred(x: LocalDate): LocalDate = x.minusDays(1)
  }

  object LocalDateTimeLines extends Time[LocalDate]

  def date(year: Int, month: Int, day: Int): LocalDate = LocalDate.of(year, month, day)

}
