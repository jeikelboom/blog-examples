package demonstration

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

import scala.math.BigDecimal.RoundingMode

object SharesModel {

  class Euro(val amount: BigDecimal) extends AnyVal {
    override def toString: String = {
      val df = NumberFormat.getCurrencyInstance(Locale.getDefault)
      s"${df.format(amount)}"
    }
  }

  object Euro {
    def apply(amount: Int): Euro = {
      val bd = BigDecimal(amount).setScale(2, RoundingMode.HALF_DOWN)/100
      new Euro(bd)
    }
    def apply(amount: BigDecimal) = new Euro(amount)
  }

  class Shares(val count: Int) extends AnyVal {
    def value(stockPrice: Euro): Euro = new Euro(count * stockPrice.amount)
    override def toString: String = s"$count shrs"
  }

  object Shares {
    def apply(count: Int): Shares = new Shares(count)
  }

  case class Record(shares: Shares, price: Euro){
    val value = shares.value(price)
    override def toString: String = s"$value = $shares * $price"
  }

  implicit def intToSHares(i: Int) = Shares(i)
  implicit def intToEuro(i: Int): Euro = Euro(i)


}
