package fp.monad

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class PythagorasTest {

    fun Triple<Int,Int,Int>.isPythagorean() =
        this.first * this.first + this.second * this.second == this.third * this.third

    val range = 1..20

    val pythagorasTriples: List<Triple<Int, Int, Int>> =
                range.flatMap {
        a ->    range.filter { b -> a < b }.flatMap {
        b ->    range.filter { c -> a*a + b*b == c*c }.map {
        c -> Triple(a,b,c) }}}

/*
select a.nr as a, b.nr as b, c.nr as c
from range a, range b, range c
where a.nr < b.nr
and a.nr * a.nr + b.nr * b.nr = c.nr * c.nr
a     b     c
 ----  ----  ----
 3     4     5
 5     12    13
 6     8     10
 8     15    17
 9     12    15
 12    16    20
 */

    @Test
    fun triples() {
        pythagorasTriples.forEach {
            it.run {
                assertTrue(isPythagorean())
                println("${it}:   ${first * first}  +  ${second * second}   == ${third * third} ")
            }
        }
    }
}