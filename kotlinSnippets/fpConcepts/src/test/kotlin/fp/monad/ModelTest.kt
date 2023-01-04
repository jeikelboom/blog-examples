package fp.monad

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ModelTest {

    @Test
    fun `cinemaVisit ClintGood can do all`(){
        val result = cinemaVisit(clint, Movie.ForAFistfulOfDollars, Drink.Beer)
        if (result is Good<Boolean>) {
            assertTrue(result.value)
        } else fail("${result}")
    }

    @Test
    fun `cinemaVisit Jake too young for ForAFistfulOfDollars`(){
        val result = cinemaVisit(jake, Movie.ForAFistfulOfDollars, Drink.Beer)
        if (result is Ugly) {
            assertEquals("Jake Blues is too young to view ForAFistfulOfDollars", result.message)
        } else fail("${result}")
    }

    @Test
    fun `cinemaVisit Jake cannot drink beer`(){
        val result = cinemaVisit(jake, Movie.TheGoodTheBadAndTheUgly, Drink.Beer)
        if (result is Ugly) {
            assertEquals("Jake Blues is too young too drink Beer", result.message)
        } else fail("${result}")
    }

    @Test
    fun `cinemaVisit Jake not too young`(){
        val result = cinemaVisit(jake, Movie.TheGoodTheBadAndTheUgly, Drink.Cola)
        if (result is Good<Boolean>) {
            assertTrue(result.value)
        } else fail("${result}")
    }

    @Test
    fun `cinemaVisitElwood cannot drink beer`() {
        val result = cinemaVisit(elwood, Movie.ForAFistfulOfDollars, Drink.Beer)
        if (result is Ugly) {
            assertEquals("Elwood Blues is too young too drink Beer", result.message)
        } else fail("${result}")
    }

    @Test
    fun `cinemaVisitElvis has no address nullpointer`(){
        val result = cinemaVisit(elvis, Movie.TheGoodTheBadAndTheUgly, Drink.Beer)
        if (result is Bad) {
            println(result)
            assertTrue (result.e is KotlinNullPointerException)
        } else fail("${result}")
    }

}