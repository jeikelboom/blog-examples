package fp.concepts

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GcdTest {


    @Test
    fun gcd42and18() {
        val gcd = GCD.gcd(18, 42)
        Assertions.assertEquals(6, gcd)
    }
}