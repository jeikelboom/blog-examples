package demonstration

import example.LocalDateTimelines.LocalDateTime.Timeline
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
  val combinedTl: Timeline[Record] = Timeline()
    .append(d1, d2, Record(2000, 100, 20))
    .append(d3, d3, Record(2400, 120, 20))
    .append(d4, d5, Record(2520, 120, 21))
    .append(d6, d7, Record(2280, 120, 19))


  val valuesTl: Timeline[Int] = Timeline()
    .append(d1, d2, 2000)
    .append(d3, d3, 2400)
    .append(d4, d5, 2520)
    .append(d6, d7, 2280)


  val sharesTl: Timeline[Int] = Timeline()
    .append(d1, d2, 100)
    .append(d3, d7, 120)


  val priceTl: Timeline[Int] = Timeline()
    .append(d1, d3, 20)
    .append(d4, d5, 21)
    .append(d6, d7, 19)

  def calculation(shares: Int, price: Int): Int = shares * price

  "values" should "be computed from shares and prices" in {
    val actual = for (shares <- sharesTl;
                         price <- priceTl) yield calculation(shares, price)
    actual shouldEqual(actual)
  }

  "combined" should "be computed from shares and prices" in {
    val actual = for (shares <- sharesTl;
                        price <- priceTl) yield (Record(price * shares, shares, price) )
    actual shouldEqual combinedTl
  }

  "prices" should "be a projection from combined" in {
    val actual = for (record <- combinedTl) yield record.price
    actual shouldEqual priceTl
  }

  "combined" should  "be split and combined" in {
    val prices = combinedTl.map(_.price)
    val shares = combinedTl.map(_.shares)
    val values =

  }
}
