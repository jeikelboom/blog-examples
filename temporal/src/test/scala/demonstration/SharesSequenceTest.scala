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

  def sumOfList(lst: List[Euro]): Euro =  lst.foldLeft(Euro(0))((a, b) => a + b)
  def totals(lstOfTimelines: List[Timeline[Euro]]): Timeline[Euro] = {
    lstOfTimelines.sequence.map(sumOfList)
  }

  "List of timelines" should "transform to timeline of lists" in {
    val allTimeLines: List[Timeline[Euro]] = List(valuesHP, valuesMicrosoft, valuesOracle)
    val output: Timeline[List[Euro]] = allTimeLines.sequence
    val summary = totals(allTimeLines)
    output.get(30).get shouldEqual List(Euro(37), Euro(18), Euro(45))
    summary.get(30).get shouldEqual Euro(100)
    println(summary)
    println(output)
  }

  /*
  output:
summaries:
Note that the otput timeline contains multiple
values after time 24, that all add up to 100
Thus they are automatically combined in one interval.
[48, 2147483647] => € 0,00
[24, 35] => € 1,00
[15, 23] => € 1,14
[14, 14] => € 1,07
[13, 13] => € 1,20
[11, 12] => € 1,10
[8, 10] => € 1,05
[5, 7] => € 0,79
[1, 4] => € 0,23
[-2147483648, 0] => € 0,00

output
[48, 2147483647] => List(€ 0,00, € 0,00, € 0,00)
[27, 35] => List(€ 0,37, € 0,18, € 0,45)
[26, 26] => List(€ 0,36, € 0,18, € 0,46)
[24, 25] => List(€ 0,36, € 0,09, € 0,55)
[15, 23] => List(€ 0,36, € 0,12, € 0,66)
[14, 14] => List(€ 0,36, € 0,05, € 0,66)
[13, 13] => List(€ 0,49, € 0,05, € 0,66)
[11, 12] => List(€ 0,49, € 0,05, € 0,56)
[8, 10] => List(€ 0,49, € 0,00, € 0,56)
[5, 7] => List(€ 0,23, € 0,00, € 0,56)
[1, 4] => List(€ 0,23, € 0,00, € 0,00)
[-2147483648, 0] => List(€ 0,00, € 0,00, € 0,00)

   */

  "sum of list" should "add all values" in {
    val aList: List[Euro] = List(Euro(20), Euro(67), Euro(13))
    val total: Euro = sumOfList(aList)
    val expected = Euro(100)
    total.amount shouldEqual expected.amount
    total shouldEqual expected
  }

}
