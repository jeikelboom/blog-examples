package fp.monad

import fp.monad.OptDemo.logOptional
import fp.monad.OptDemo.v1
import fp.monad.OptDemo.v2
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.*



class OptDemoTest {
    val empty:Optional<Double> = Optional.empty()

    @Test
    fun logtest() {
        assertEquals(2.0, logOptional(100.0).get())
        assertEquals(-2.0, logOptional(0.01).get())
        assertEquals(empty, logOptional(-2.0))
    }

    @Test
    fun flatmapTest() {
        assertEquals(empty, v1)
        assertEquals(Optional.of(1.0), v2)
    }
}