package demonstration

import SharesModel._

import example.IntegerTimeUnit.IntegerTimelines.Timeline
import org.scalatest.{FlatSpec, Matchers}


class SharesTimelineIntegerTest extends FlatSpec with Matchers {

  val combinedTl: Timeline[Record] = Timeline()
    .append(1, 2, Record(100, 2000))
    .append(3, 3, Record(120, 2000))
    .append(4, 5, Record(120, 2100))
    .append(6, 7, Record(120, 1900))

  val valuesTl: Timeline[Euro] = Timeline()
    .append(1, 2, Euro(200000))
    .append(3, 3, Euro(240000))
    .append(4, 5, Euro(252000))
    .append(6, 7, Euro(228000))

  val sharesTl: Timeline[Shares] = Timeline()
    .append(1, 2, Shares(100))
    .append(3, 7, Shares(120))

  val priceTl: Timeline[Euro] = Timeline()
    .append(1, 3, Euro(2000))
    .append(4, 5, Euro(2100))
    .append(6, 7, Euro(1900))

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
                      price <- priceTl) yield computeValue(shares, price)
    actual shouldEqual actual
    println(s"values = $actual")
  }

  "combined" should  "be split and combined" in {
    val prices = combinedTl.map(_.price)
    val shares = combinedTl.map(_.shares)
    val actual = for (sh <- shares;
                      pr <- prices) yield Record( sh, pr)
    actual shouldEqual combinedTl
  }

}
