package fp.concepts.high

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class HighOrderTest {

    @Test
    fun outcomes() {
        assertEquals(listOf(3, 5, 5, 3), lengths)
        assertEquals(4, size)
        assertEquals(16, sum)
        assertEquals(false, anyNullStrings)
    }
    @Test
    fun lifting() {
        val lifted: (List<String>) -> List<Int> = liftFunction { it.length }
        assertEquals(listOf(3, 5, 5, 3), lifted(mylist))
    }

    @Test
    fun mapByFold() {
        assertEquals(listOf(3, 5, 5, 3), mylist.mapByFold { it.length })
    }

}