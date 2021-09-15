

trait Expr {
  def eval() : Int
}
class Constant(val value: Int) extends Expr {
  override def eval(): Int = value
}
class Plus(val left: Expr, val right: Expr) extends Expr {
  override def eval(): Int = left.eval() + right.eval()
}

val e1 = new Constant(1)
val e2 = new Constant(3)
val e3 = new Plus(e1, e2)
val outcome = e3.eval()
// outcome: Int = 4
//-----------------------
class Minus(val left: Expr, val right: Expr) extends Expr {
  override def eval() = left.eval() - right.eval()
}

val e4 = new Constant(10)
val e5 = new Minus(e4, e3)
val outcome2 = e5.eval()
// outcome2: Int = 6