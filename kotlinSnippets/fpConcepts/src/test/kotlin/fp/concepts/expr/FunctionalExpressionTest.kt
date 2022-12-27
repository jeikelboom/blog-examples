package fp.concepts.expr

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FunctionalExpressionTest {

    @Test
    fun functionalSolution() {
        val expression: Expression =
            Plus(
                Plus(Value(13), Value(5)),
                Plus(Value(7), Value(11))
            )
        assertEquals(36, evaluate(expression))
    }

    @Test
    fun ooSolution() {
        val expr: OoExpression = OoPlus(
            OoPlus(OoValue(13), OoValue(5)),
            OoPlus(OoValue(7), OoValue(11))
        )
        assertEquals(36, expr.eval())
    }

    @Test
    fun functionalSolution2() {
        val expression: Expression =
            Plus(
                Plus(Value(13), Value(5)),
                Plus(Value(17), Value(11))
            )
        assertEquals("((13 + 5) + (17 + 11))", prettyPrint(expression))
    }

    @Test
    fun ooSolution2() {
        val expr: OoExpression = OoPlus(
            OoMinus(OoValue(13), OoValue(5)),
            OoPlus(OoValue(7), OoValue(11))
        )
        assertEquals(26, expr.eval())
    }

}