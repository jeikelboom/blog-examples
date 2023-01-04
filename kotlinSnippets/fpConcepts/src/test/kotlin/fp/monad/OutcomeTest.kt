package fp.monad

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import fp.model.*

class OutcomeTest {


    val mailMovieTicketL = liftToOutcomeMonad({ x:Person, y: String -> mailMovieTicket(x,y)})
    @Test
    fun invokeGood() {
        val result = Outcome({clint.lastName})
        assertEquals("Eastwood", (result as Good<String>).returnValue)
    }
    @Test
    fun invokeBad() {
        val result = Outcome({throw IllegalArgumentException("ok bad")})
        assertEquals("ok bad", (result as Bad<String>).e.message)
    }

    val cinemaVisit = {visitor: Person, movie: Movie, drink: Drink ->
        liftValueToOutcome(visitor).flatMap {
            person -> buyMovieTicket(person, movie).flatMap {
            ticket -> buyADrink(person, drink).flatMap {
            drink -> mailMovieTicketL(person, ticket)
        } } }
    }


    @Test
    fun flatmappedClintGood(){
        val result = cinemaVisit(clint, Movie.ForAFistfulOfDollars, Drink.Beer)
        if (result is Good<Boolean>) {
            assertTrue(result.returnValue)
        } else fail("${result}")
    }

    @Test
    fun flatmappedJakeUgly1(){
        val result = cinemaVisit(jake, Movie.ForAFistfulOfDollars, Drink.Beer)
        if (result is Ugly) {
            assertEquals("Jake Blues is too young to view ForAFistfulOfDollars", result.message)
        } else fail("${result}")
    }

    @Test
    fun flatmappedJakeUgly2(){
        val result = cinemaVisit(jake, Movie.TheGoodTheBadAndTheUgly, Drink.Beer)
        if (result is Ugly) {
            assertEquals("Jake Blues is too young too drink Beer", result.message)
        } else fail("${result}")
    }

    @Test
    fun flatmappedJakeGood(){
        val result = cinemaVisit(jake, Movie.TheGoodTheBadAndTheUgly, Drink.Cola)
        if (result is Good<Boolean>) {
            assertTrue(result.returnValue)
        } else fail("${result}")
    }

    @Test
    fun flatmappedElwoodUgly() {
        val result = cinemaVisit(elwood, Movie.ForAFistfulOfDollars, Drink.Beer)
        if (result is Ugly) {
            assertEquals("Elwood Blues is too young too drink Beer", result.message)
        } else fail("${result}")
    }


    @Test
    fun flatmappedElvisBad(){
        val result = cinemaVisit(elvis, Movie.TheGoodTheBadAndTheUgly, Drink.Beer)
        if (result is Bad) {
            println(result)
            assertTrue (result.e is KotlinNullPointerException)
        } else fail("${result}")
    }

    @Test
    fun liftToOutcomeFunctorTest() {
        val aFunction: (String) -> Int = {it.length}
        val aLiftedFunction: (Outcome<String>) -> Outcome<Int> = liftToOutcomeFunctor(aFunction)
        assertEquals(5, (aLiftedFunction(Good("hello")) as Good<Int>).returnValue)
        assertEquals("ugly", (aLiftedFunction(Ugly("ugly")) as Ugly<Nothing>).message)
    }

}