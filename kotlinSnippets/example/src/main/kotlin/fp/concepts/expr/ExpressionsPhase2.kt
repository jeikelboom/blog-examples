package fp.concepts.expr

    fun prettyPrint(e: Expression) : String =
        when (e) {
            is Plus -> "(${prettyPrint(e.left)} + ${prettyPrint(e.right)})"
            is Value -> "${e.value}"
        }

    class OoMinus(val left: OoExpression, val right: OoExpression): OoExpression() {
        override fun eval(): Int = left.eval() - right.eval()
    }
