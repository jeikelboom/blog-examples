package demonstration

import SharesModel._
import example.IntegerTimeUnit.IntegerTimelines._
import org.scalatest.{FlatSpec, Matchers}
import cats.instances.all._
import cats.syntax.all._

class SharesSequenceTest extends FlatSpec with Matchers{


  val valuesHP: Timeline[Euro] = Timeline()
    .append(Int.MinValue,0, Euro(0))
    .append(1, 7, Euro(23))
    .append(8, 13, Euro(49))
    .append(14, 24, Euro(36))
    .append(25, 26, Euro(36))
    .append(27, 37, Euro(37))
    .append(38, Int.MaxValue, Euro(0))

  val valuesMicrosoft: Timeline[Euro] = Timeline()
    .append(Int.MinValue,10, Euro(0))
    .append(11, 14, Euro(5))
    .append(15, 23, Euro(12))
    .append(24, 25, Euro(9))
    .append(26, 47, Euro(18))
    .append(48, Int.MaxValue, Euro(0))

  val valuesOracle: Timeline[Euro] = Timeline()
    .append(Int.MinValue,4, Euro(0))
    .append(5, 12, Euro(56))
    .append(13, 23, Euro(66))
    .append(24, 25, Euro(55))
    .append(26, 26, Euro(46))
    .append(27, 35, Euro(45))
    .append(63, 57, Euro(47))
    .append(48, Int.MaxValue, Euro(0))

  "List of timelines" should "transform to timeline of lists" in {
    val allTimeLines: List[Timeline[Euro]] = List(valuesHP, valuesMicrosoft, valuesOracle)
    val output: Timeline[List[Euro]] = allTimeLines.sequence
    val summary = output.map(v => v.foldLeft(Euro(0))((a, b) => a + b))
    output.get(30).get shouldEqual List(Euro(37), Euro(18), Euro(45))
    summary.get(30).get shouldEqual Euro(100)
    println(summary)
    println(output)

  }

  "sum of list" should "add all values" in {
    val aList: List[Euro] = List(Euro(20), Euro(67), Euro(13))
    val total: Euro = aList.foldLeft(Euro(0))((a, b) => a + b)
    total shouldEqual Euro(100)
  }

}
