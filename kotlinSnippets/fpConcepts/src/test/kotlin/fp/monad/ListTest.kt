package fp.monad

import org.junit.jupiter.api.Test


class ListTest {



    @Test
    fun listDemo() {
        val persons = people.flatMap {
                person -> addressList.filter { person.addressID == it.addressID }.map {
                address ->  person to address
        } }
        persons.forEach({println("${it.first.firstName}  ${it.first.lastName} lives in ${it.second.town}")})
    }
}