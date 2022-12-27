package fp.monad

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class PythagorasTest {

    fun Triple<Int,Int,Int>.isPythagorean() =
        this.first * this.first + this.second * this.second == this.third * this.third

    val range = 1..20
    val range1 = 1..1

    val pythagorasTriples =
        range.flatMap {
        a -> range.flatMap {
        b -> range.flatMap {
        c  -> range1
            .filter { a < b && a*a + b*b == c*c}
            .map {Triple(a,b,c)}}} }



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