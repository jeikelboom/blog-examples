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
        val lifted: (List<String>) -> List<Int> = liftIntoWorldOfLists { it.length }
        assertEquals(listOf(3, 5, 5, 3), lifted(mylist))
        assertEquals(listOf(1,2,3,4), lifted(listOf("a", "bb", "ccc", "dddd")))
    }

    @Test
    fun mapByFold() {
        assertEquals(listOf(3, 5, 5, 3), mylist.mapByFold { it.length })
        assertEquals(listOf(1,2,3,4), listOf("a", "bb", "ccc", "dddd").mapByFold { it.length })
    }

}