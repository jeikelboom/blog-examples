package fp.monad

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class PythagorasTest {

    fun Triple<Int,Int,Int>.isPythagorean() =
        this.first * this.first + this.second * this.second == this.third * this.third

    val range1to40 = 1..40

    val pythagorasTriples: List<Triple<Int, Int, Int>> =
        range1to40.flatMap {
            i -> (i..20) .flatMap {
            j -> (1..20).map {
            k  -> Triple(i,j,k) }.filter { it.isPythagorean() }
            } }

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