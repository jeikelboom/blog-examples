package demonstration

import SharesModel._
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

  val combinedTl: Timeline[Record] = Timeline()
    .append(d1, d2, Record(100, 2000))
    .append(d3, d3, Record(120, 2000))
    .append(d4, d5, Record(120, 2100))
    .append(d6, d7, Record(120, 1900))


  val valuesTl: Timeline[Euro] = Timeline()
    .append(d1, d2, Euro(200000))
    .append(d3, d3, Euro(240000))
    .append(d4, d5, Euro(252000))
    .append(d6, d7, Euro(228000))


  val sharesTl: Timeline[Shares] = Timeline()
    .append(d1, d2, Shares(100))
    .append(d3, d7, Shares(120))


  val priceTl: Timeline[Euro] = Timeline()
    .append(d1, d3, Euro(2000))
    .append(d4, d5, Euro(2100))
    .append(d6, d7, Euro(1900))

  println(s"combinedTL $combinedTl")



  "prices" should "be a projection from combined" in {
    val actual = for (record <- combinedTl) yield record.price
    actual shouldEqual priceTl
    println(s"prices = $actual")
  }

  "shares" should "be a projection from combined" in {
    val actual = for (record <- combinedTl) yield record.shares
    actual shouldEqual sharesTl
    println(s"shares = $actual")
  }

  "values" should "be computed from shares and prices" in {
    val actual = for (shares <- sharesTl;
                      price <- priceTl) yield shares.value(price)
    actual shouldEqual(actual)
    println(s"values = $actual")
  }

  "combined" should  "be split and combined" in {
    val prices = combinedTl.map(_.price)
    val shares = combinedTl.map(_.shares)
    val actual = for (sh <- shares;
                      pr <- prices) yield (Record( sh, pr) )
    actual shouldEqual combinedTl
  }
}
