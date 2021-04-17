package demonstration

import org.scalatest.{FlatSpec, Matchers}
import SharesModel._

class SharesModelTest extends FlatSpec with Matchers{

  "2020" should "be € 20,20 " in {
    val bedrag: Euro = Euro(2020)
    val tekst: String = bedrag.toString
    tekst shouldEqual "€ 20,20"
  }
  "-28,95" should "be € -28,95 " in {
    val bedrag: Euro = Euro(-2895)
    val tekst: String = bedrag.toString
    tekst shouldEqual "€ -28,95"
  }

  "20" should "be € 0,20 " in {
    val bedrag: Euro = Euro(20)
    val tekst: String = bedrag.toString
    tekst shouldEqual "€ 0,20"
  }

}
