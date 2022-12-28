package fp.concepts

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test



class CurryingTest {
    fun <P1, P2, R> Function2<P1, P2, R>.curried(): (P1) -> (P2) -> R  =
        { p1 -> { p2 -> this(p1, p2) } }
    
    @Test
    fun addTupled(){
        val add: (Int, Int) -> Int = { x, y -> x + y}
        val addCurried: (Int) -> (Int) -> Int = add.curried()
        val incr = addCurried(1)
        val six = incr(5)
        assertEquals(6, six)
    }

    @Test
    fun addCurried() {
        // -> is right associative, so read (Int) -> ((Int) -> Int)
        val add: (Int) -> (Int) -> Int = { x: Int, y: Int -> x + y}.curried()
        val incr = add(1)
        val six = incr(5)
        assertEquals(6, six)
    }

}