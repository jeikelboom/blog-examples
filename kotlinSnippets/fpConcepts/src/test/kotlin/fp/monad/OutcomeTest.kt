package fp.monad

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class OutcomeTest {

    @Test
    fun liftToOutcomeFunctorTest() {
        val aFunction: (String) -> Int = {it.length}
        val aLiftedFunction: (Outcome<String>) -> Outcome<Int> = liftToOutcomeFunctor(aFunction)
        assertEquals(5, (aLiftedFunction(Good("hello")) as Good<Int>).value)
        assertEquals("ugly", (aLiftedFunction(Ugly("ugly")) as Ugly<Nothing>).message)
    }

    @Test
    fun liftToOutcomeMonadTest() {
        val aFunction: (String) -> Int = {it.length}
        val aLiftedFunction: (String) -> Outcome<Int> = liftToOutcomeMonad<String, Int> (aFunction)
        assertEquals(5, (aLiftedFunction("hello") as Good<Int>).value)
        assertEquals("ugly", ((Ugly<String>("ugly")).flatMap { a -> aLiftedFunction(a) } as Ugly<Nothing>).message)
    }

}