package fp.monad

import org.junit.jupiter.api.Test

class OutcomeTest {

    data class Address(val street: String, val houseNr: Int, val town: String)
    data class Person(val firstName: String, val lastName: String, val age: Int,val address: Int)

    val addressBook: Map<Int, Address> = mapOf(
        1 to Address("Herengracht", 24, "Amsterdam"),
        2 to  Address("Herengracht", 35, "Amsterdam")
    )
    val person1 = Person("Clint", "Eastwood", 45, 1)
    val person2 = Person("Elwood", "Blues", 17, 2)
    val person3 = Person("Jake", "Blues", 24, 3)

    fun buyMovieTicket(person: Person) : Outcome<String> =
        if (person.age >=18) Good("45")
        else person.run{ Ugly("${firstName} ${lastName} is too young") }

    @Test
    fun good() {

    }
}