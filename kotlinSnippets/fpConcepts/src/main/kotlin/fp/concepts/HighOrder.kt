package fp.concepts.high

val mylist = listOf("The", "quick", "brown", "fox")
val lengths = mylist.map { s -> s.length }
val size = mylist.fold(0, {total, _  -> total + 1})
val sum = lengths.fold(0, {total, value -> total + value})
val anyNullStrings = mylist.fold(false,{total, value -> total || "" == value})

fun  <A, B> liftIntoWorldOfLists(f: (A) -> B):  (List<A>) -> List<B> =
    {la -> la.fold(listOf(), {l, a -> l + f(a)})}

fun  <A, B> List<A>.mapByFold(f: (A) -> B):  List<B> =
    this.fold(listOf(), {total,value -> total + f(value)})







