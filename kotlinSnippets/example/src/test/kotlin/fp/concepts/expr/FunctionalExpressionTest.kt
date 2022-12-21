package fp.concepts.expr

import org.junit.jupiter.api.Test
import fp.concepts.expr.*
import org.junit.jupiter.api.Assertions.*

class FunctionalExpressionTest {

    @Test
    fun functionalSolution() {
        val expression: Expression =
            Plus(
                Plus(Value(3), Value(5)),
                Plus(Value(7), Value(11))
            )
        assertEquals(26, evaluate(expression))
    }

    @Test
    fun ooSolution() {
        val expr: OoExpression = OoPlus(
            OoPlus(OoValue(3), OoValue(5)),
            OoPlus(OoValue(7), OoValue(11))
        )
        assertEquals(26, expr.eval())
    }
}