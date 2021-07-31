package demonstration

import SharesModel._
import example.IntegerTimeUnit.IntegerTimelines._
import org.scalatest.{FlatSpec, Matchers}
import cats.syntax


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
    val actual1 = for (record <- combinedTl) yield record.price
    val actual2 = combinedTl.map(_.price)
    actual1 shouldEqual priceTl
    actual2 shouldEqual priceTl
    println(s"prices = $actual1")
  }

  "shares" should "be a projection from combined" in {
    val actual1 = for (record <- combinedTl) yield record.shares
    val actual2 = combinedTl.map(_.shares)
    actual1 shouldEqual sharesTl
    actual2 shouldEqual sharesTl
    println(s"shares = $actual1")
  }

  "values" should "be computed from shares and prices with for" in {
    val actual = for (shares <- sharesTl;
                       price <- priceTl) yield computeValue(price, shares)
    actual shouldEqual valuesTl
  }

  "values" should "be computed from shares and prices with ap2" in {
    val computeValuePure: Timeline[(Shares, Euro) => Euro] =
      timeLineApplicative.pure((a,b) =>computeValue(b, a))
    val actual = timeLineApplicative.ap2(computeValuePure)(sharesTl, priceTl)
    actual shouldEqual valuesTl
  }

  "combined" should  "be split and combined" in {
    val prices = combinedTl.map(_.price)
    val shares = combinedTl.map(_.shares)
    val computeValuePure: Timeline[(Shares, Euro) => Record] = timeLineApplicative.pure((a,b) =>Record(a, b))
    val actual = timeLineApplicative.ap2(computeValuePure)(sharesTl, priceTl)
    actual shouldEqual combinedTl
    sharesTl.
    val actual2 = (sharesTl, priceTl).mapN(computeValuePure)
  }

}
