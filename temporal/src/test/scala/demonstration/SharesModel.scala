package demonstration

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

import scala.math.BigDecimal.RoundingMode

object SharesModel {

  class Euro(val amount: BigDecimal) extends AnyVal {
    override def toString: String = {
      val df = NumberFormat.getCurrencyInstance()
      s"${df.format(amount)}"
    }

    def *(shares: Shares): Euro = new Euro(shares.count * amount)
  }

  class Shares(val count: Int) extends AnyVal {
    override def toString: String = s"$count shrs"
  }

  def computeValue(stockprice: Euro, shares: Shares): Euro =
    stockprice * shares

  case class Record(shares: Shares, price: Euro){
    val value = computeValue(price, shares)
    override def toString: String = s"$value = $shares * $price"
  }

  object Euro {
    def apply(amount: Int): Euro = {
      val bd = BigDecimal(amount).setScale(2, RoundingMode.HALF_DOWN)/100
      new Euro(bd)
    }
    def apply(amount: BigDecimal) = new Euro(amount)
  }


  object Shares {
    def apply(count: Int): Shares = new Shares(count)
  }

  implicit def intToSHares(i: Int): Shares = Shares(i)
  implicit def intToEuro(i: Int): Euro = Euro(i)

}
