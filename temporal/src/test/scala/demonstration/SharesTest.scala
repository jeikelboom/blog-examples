package demonstration

import example.LocalDateTimelines.LocalDateTimed._
import example.LocalDateTimelines.date
import java.time.LocalDate

import org.scalatest.{FlatSpec, Matchers}


class SharesTest extends FlatSpec with Matchers {
  val d1 :LocalDate = date(2021, 3,1)
  val d2 :LocalDate = date(2021, 3,2)
  val d3 :LocalDate = date(2021, 3,3)
  val d4 :LocalDate = date(2021, 3,4)
  val d5 :LocalDate = date(2021, 3,5)
  val d6 :LocalDate = date(2021, 3,6)
  val d7 :LocalDate = date(2021, 3,7)

  case class Record(value: Int, shares: Int, price: Int)
  val combinedTl: Timeline[Record] = Timeline(List(
      IntervalData(d1, d2, Record(2000, 100, 20)),
      IntervalData(d3, d3, Record(2400, 120, 20)),
      IntervalData(d4, d5, Record(2520, 120, 21)),
      IntervalData(d6, d7, Record(2280, 120, 19))
  ))

  val valuesTl: Timeline[Int] = Timeline(List(
    IntervalData(d1, d2, 2000),
    IntervalData(d3, d3, 2400),
    IntervalData(d4, d5, 2520),
    IntervalData(d6, d7, 2280)
  ))

  val sharesTl: Timeline[Int] = Timeline(List(
    IntervalData(d1, d2, 100),
    IntervalData(d3, d7, 120)
  ))

  val priceTl: Timeline[Int] = Timeline(List(
    IntervalData(d1, d3, 20),
    IntervalData(d4, d5, 21),
    IntervalData(d6, d7, 19)
  ))

  "values" should "be computed from shares and prices" in {
    val computed = for (shares <- sharesTl;
                         price <- priceTl) yield (shares * price)
    //computed shouldEqual(valuesTl)
    println("values computed ")
    println(computed)
  }

  "combined" should "be computed from shares and prices" in {
    val computed = for (shares <- sharesTl;
                        price <- priceTl) yield (Record(price * shares, shares, price) )
    //computed shouldEqual combinedTl
    println("combined computed")
    println(computed)
  }

  "prices" should "be a projection from combined" in {
    val computed = for (record <- combinedTl) yield record.price
    println("prices computed")
    println(computed)
  }

}
