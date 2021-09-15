sealed trait Expr
case class Plus(left: Expr, right: Expr) extends Expr
case class Constant(i: Int) extends Expr

def eval(e: Expr) :Int = {
  e match {
    case Plus(l, r) => eval(l) + eval(r)
    case Constant(c) => c
  }
}
val e1 = Constant(1)
val e2 = Constant(3)
val e3 = Plus(e1, e2)
val outcome = eval(e3)
//-----------------------
def pretty(e: Expr) : String = {
  e match {
    case Plus(l,r) => s"(${pretty(l)} + ${pretty(r)})"
    case Constant(c) => s"${c}"
  }
}
val printout = pretty(e3)
//printout: String = (1 + 3)