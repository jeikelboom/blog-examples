package fp.concepts.expr

/*
 A functional solution to the expression problem.
 We see a verb here: evaluate
 */
    sealed class Expression()

    data class Plus(val left: Expression, val right: Expression): Expression()
    data class Value(val value:Int): Expression()

    fun evaluate(expression: Expression): Int =
        when(expression) {
            is Plus -> evaluate(expression.left) + evaluate(expression.right)
            is Value -> expression.value
        }

/*
     OO style solution to the expression problem
     We see nouns here as top level elements
 */

    abstract class OoExpression(){
        abstract fun eval(): Int
}
    class OoPlus(val left: OoExpression, val right: OoExpression): OoExpression(){
        override fun eval(): Int = left.eval() + right.eval()
    }

    class OoValue(val v: Int):OoExpression() {
        override fun eval(): Int = v
    }