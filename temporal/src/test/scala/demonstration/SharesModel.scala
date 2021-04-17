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
  }

  class Shares(val count: Int) extends AnyVal {
    override def toString: String = s"$count shrs"
  }

  def computeValue(shares: Shares, stockprice: Euro): Euro = new Euro(shares.count * stockprice.amount)

  case class Record(shares: Shares, price: Euro){
    val value = computeValue(shares, price)
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


  implicit def intToSHares(i: Int) = Shares(i)
  implicit def intToEuro(i: Int): Euro = Euro(i)


}
