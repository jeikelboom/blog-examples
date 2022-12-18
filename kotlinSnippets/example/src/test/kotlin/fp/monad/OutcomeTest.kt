package fp.monad

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import fp.concepts.TheModel.Person
import fp.concepts.TheModel.Address
import fp.concepts.TheModel.addressBook
import fp.concepts.TheModel.person1
import fp.concepts.TheModel.person2
import fp.concepts.TheModel.person3


import arrow.core.curried
class OutcomeTest {

    fun buyMovieTicket(person: Person) : Outcome<String> =
        if (person.age >=18) Good("Seat 45")
        else person.run{ Ugly("${firstName} ${lastName} is too young") }

    fun mailMovieTicket (person: Person, ticket: String): Boolean  {
        val address: Address = addressBook[person.address]!!
        println("${ticket} to ${address}")
        return true
    }
    fun buyADrink(person: Person, alcoholic: Boolean): Outcome<String> =
        (if (alcoholic && person.age >=18) Good("Beer")
        else if (! alcoholic) Good("Cola")
        else person.run{ Ugly("${firstName} ${lastName} is too young too drink beer") })


    val mailMovieTicketL = lift({x:Person,y: String -> mailMovieTicket(x,y)})
    @Test
    fun invokeGood() {
        val result = Outcome({person1.lastName})
        assertEquals("Eastwood", (result as Good<String>).returnValue)
    }
    @Test
    fun invokeBad() {
        val result = Outcome({throw IllegalArgumentException("ok bad")})
        assertEquals("ok bad", (result as Bad<String>).e.message)
    }
    @Test
    fun flatmappedGood(){
        val result =
                pure(person1).flatMap {
                person -> buyMovieTicket(person).flatMap {
                ticket -> mailMovieTicketL(person, ticket)
        } }
        if (result is Good<Boolean>) {
            assertTrue(result.returnValue)
        } else fail("${result}")
    }



    @Test
    fun flatmappedUgly(){
        val result =
                pure(person2).flatMap {
                person -> buyMovieTicket(person).flatMap {
                ticket -> mailMovieTicketL(person, ticket)
        } }
        if (result is Ugly) {
            assertEquals("Elwood Blues is too young", result.message)
        } else fail("${result}")
    }

    @Test
    fun flatmappedUgly2() {
        val result =
                pure(person2).flatMap {
                person -> buyADrink(person, true).flatMap {
                drink -> buyMovieTicket(person).flatMap {
                ticket -> mailMovieTicketL(person, ticket)
        } } }
        if (result is Ugly) {
            assertEquals("Elwood Blues is too young too drink beer", result.message)
        } else fail("${result}")
    }

    @Test
    fun flatmappedBad(){
        val result =
                pure(person3).flatMap {
                person -> buyMovieTicket(person).flatMap {
                ticket -> mailMovieTicketL(person, ticket)
        } }
        if (result is Bad) {
            println(result)
            assertTrue (result.e is kotlin.KotlinNullPointerException)
        } else fail("${result}")
    }


}