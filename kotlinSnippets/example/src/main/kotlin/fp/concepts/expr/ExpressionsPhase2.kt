package fp.concepts.expr

/*
    Challenge:
    Add a pretty printer functionality and a minus operator to the solution
    You cannot modify the first solution

    Functional style: we can easily add a function, bt not a data class
    Object style: we can easily add a class, but not a function
 */

    fun prettyPrint(e: Expression) : String =
        when (e) {
            is Plus -> "(${prettyPrint(e.left)} + ${prettyPrint(e.right)})"
            is Value -> "${e.value}"
        }

    class OoMinus(val left: OoExpression, val right: OoExpression): OoExpression() {
        override fun eval(): Int = left.eval() - right.eval()
    }
