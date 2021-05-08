package example

import example.IntegerTimeUnit.IntegerTimelines._
import org.scalatest.{FlatSpec, Matchers}


class TemporalMonadSimpleTest  extends FlatSpec with Matchers {

  val timeline1: Timeline[Int]= Timeline()
    .append(9,15,  10)
    .append(16, 17, 20)
    .append(18,35, 30)

  val timeline2: Timeline[Int]= Timeline()
    .append(9,13,  100)
    .append(14, 18, 200)
    .append(19,35, 300)

  val timeline3: Timeline[Int]= Timeline()
    .append(9,13,  1000)
    .append(14, 25, 2000)
    .append(26,35, 3000)

  val expected : Timeline[Int] = Timeline()
    .append(9, 13, 1110)
    .append(14,15, 2210)
    .append(16,17, 2220)
    .append(18,18, 2230)
    .append(19,25,2330)
    .append(26,35,3330)


  "flatMap" should "work" in {
    val result1 = timeline1.flatMap(x1 => timeline2.flatMap(x2 => timeline3.map(x3 => x3 + x2 + x1)))
    result1 shouldEqual  expected
  }

  "for" should "give the same result" in {
    val result1 = for (i1 <- timeline1;
                       i2 <- timeline2;
                       i3 <- timeline3) yield i1 + i2 + i3
    result1 shouldEqual  expected
  }


}
