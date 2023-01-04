package fp.model

import fp.monad.Good
import fp.monad.Outcome
import fp.monad.Ugly

fun Int.modulo(divider: Int) = this - divider * this.div(divider)

data class Address(val addressID:
                   Int, val street: String, val houseNr: Int,
                   val town: String){
    val index = addressID to this
}
data class Person(val personID: Int,
                  val firstName: String, val lastName: String,
                  val age: Int,
                  val addressID: Int)

val address1 = Address(1,"Herengracht", 24, "Amsterdam")
val address2 = Address(2,"Coolsingel", 35, "Rotterdam")
val address3 = Address(3,"Croeselaan", 29, "Utrecht")

val clint = Person(21,"Clint", "Eastwood", 45, 1)
val elwood = Person(22,"Elwood", "Blues", 17, 2)
val jake = Person(23,"Jake", "Blues", 11, 3)
val elvis = Person(24,"Elvis", "Presley", 48, 4)

val people = listOf<Person>(clint, elwood, jake, elvis)
val addressList= listOf<Address>(address1, address2, address3)
val addressBook: Map<Int, Address> = mapOf(
    address1.index,
    address2.index,
    address3.index
)

enum class Drink(val alcoholic: Boolean){
    Cola(false),
    Beer(true)
}

enum class Movie(val minimumAge: Int) {
    TheGoodTheBadAndTheUgly(0),
    ForAFistfulOfDollars(12)
}

fun buyMovieTicket(person: Person, movie: Movie) : Outcome<String> =
    if (person.age >= movie.minimumAge) Good("Seat 45")
    else Ugly("${person.firstName} ${person.lastName} is too young to view ${movie.name}")

fun mailMovieTicket (person: Person, ticket: String): Boolean  {
    val address: Address = addressBook[person.addressID]!!
    println("mailed ${ticket} to ${address}")
    return true
}

fun buyADrink(person: Person, drink: Drink): Outcome<String> =
    (if (drink.alcoholic && person.age >=18) Good("Beer")
    else if (! drink.alcoholic) Good("Cola")
    else person.run{ Ugly("${firstName} ${lastName} is too young too drink ${drink.name}") })



