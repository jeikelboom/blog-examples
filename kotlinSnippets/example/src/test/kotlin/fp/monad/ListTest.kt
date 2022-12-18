package fp.monad

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import fp.model.*



class ListTest {



    @Test
    fun listDemo() {
        val to1: Pair<Person, Address> = person1 to address1
        val persons = people.flatMap {
                person -> addressList.filter { person.address == it.addressID }.map {
                address ->  person to address
        } }
        persons.forEach({println("${it.first.firstName}  ${it.first.lastName} lives in ${it.second.town}")})
    }
}