package demonstration

import java.time.LocalDate

import example.IntegerTimeUnit.IntegerTimelines.Timeline
import org.scalatest.{FlatSpec, Matchers}


class SharesTestInteger extends FlatSpec with Matchers {

  case class Record(value: Int, shares: Int, price: Int)
  val combinedTl: Timeline[Record] = Timeline()
    .append(1, 2, Record(2000, 100, 20))
    .append(3, 4, Record(2400, 120, 20))
    .append(4, 5, Record(2520, 120, 21))
    .append(6, 7, Record(2280, 120, 19))

  val valuesTl: Timeline[Int] = Timeline()
    .append(1, 2, 2000)
    .append(3, 3, 2400)
    .append(4, 5, 2520)
    .append(6, 7, 2280)

  val sharesTl: Timeline[Int] = Timeline()
    .append(1, 2, 100)
    .append(3, 7, 120)

  val priceTl: Timeline[Int] = Timeline()
    .append(6, 7, 19)
    .append(4, 5, 21)
    .append(1, 3, 20)

  "values" should "be computed from shares and prices" in {
    val actual = for (shares <- sharesTl;
                         price <- priceTl) yield (shares * price)
    println(s"actual values: \n${actual}")
    println(s"expected values:\n${valuesTl}")
    actual shouldEqual valuesTl
  }

  "combined" should "be computed from shares and prices" in {
    val actual = for (shares <- sharesTl;
                        price <- priceTl) yield (Record(price * shares, shares, price) )
      println(s"actual combined: \n${actual}")
      println(s"expected combined:\n${valuesTl}")
      actual shouldEqual combinedTl

  }

  "prices" should "be a projection from combined" in {
    val actual = for (record <- combinedTl) yield record.price
    println(s"actual prices: \n${actual}")
    println(s"expected prices:\n${valuesTl}")
    actual shouldEqual priceTl

  }

}
