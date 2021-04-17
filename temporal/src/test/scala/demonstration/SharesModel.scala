package demonstration

object SharesModel {

  class Euro(val amount: Int) extends AnyVal {
    def cents = amount % 100
    def euros = amount/ 100
    override def toString: String = s"€ $euros.$cents"
  }

  class Shares(val count: Int) extends AnyVal {
    def value(stockPrice: Euro): Euro = new Euro(count * stockPrice.amount)

    override def toString: String = s"$count shrs"
  }

}
