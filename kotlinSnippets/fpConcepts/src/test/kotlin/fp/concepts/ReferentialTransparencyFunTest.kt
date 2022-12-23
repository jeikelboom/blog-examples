package fp.concepts

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ReferentialTransparencyFunTest {

    @Test
    fun oddHelloFun() {
        Assertions.assertTrue(fp.concepts.ReferentialTransparencyFun.isHelloOdd())
    }

    @Test
    fun oddHelloVal() {
        Assertions.assertTrue(fp.concepts.ReferentialTransparencyVal.isHelloOdd)
    }
}