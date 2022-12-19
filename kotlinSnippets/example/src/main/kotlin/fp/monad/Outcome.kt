package fp.monad

sealed class   Outcome<out RV> {
    abstract fun <A> map(mapper: (RV) -> A): Outcome<A>
    abstract fun <A> flatMap(monadicFunction: (RV) -> Outcome<A>): Outcome<A>
    companion object{
        operator fun <T> invoke(func: () -> T): Outcome<T> =
            try {
                Good(func())
            } catch (error: Exception) {
                Bad(error)
            }
    }
}
class Good<RV>(val returnValue: RV) : Outcome<RV>() {
    override fun <A> map(mapper: (RV) -> A): Outcome<A> = Good(mapper(returnValue))
    override fun <A> flatMap(monadicFunction: (RV) -> Outcome<A>): Outcome<A> = monadicFunction(returnValue)
}
class Bad<RV>(val e: Exception): Outcome<RV>() {
    override fun <A> map(mapper: (RV) -> A)= Bad<Nothing>(e)
    override fun <A> flatMap(monadicFunction: (RV) -> Outcome<A>)= Bad<Nothing>(e)
}
class Ugly<RV>(val message:String) : Outcome<RV>() {
    override fun <A> map(mapper: (RV) -> A): Outcome<A> =
        Ugly<Nothing>(message)
    override fun <A> flatMap(monadicFunction: (RV) -> Outcome<A>): Outcome<A> =
        Ugly<Nothing>(message)
}

fun <A> liftValueToOutcome(a: A) = Good(a)
fun <Z> liftToOutcome(f: () -> Z) = Good(f())
fun<A, Z> liftToOutcome(f: (A) -> Z): (A) -> Outcome<Z> =  { a -> Outcome({f(a)})}
fun<A, B, Z> liftToOutcome(f: (A, B) -> Z): (A, B) -> Outcome<Z> =  { a, b -> Outcome({f(a, b)})}

fun <A,B,C> composeFun(f: (A) -> B, g: (B) -> C): (A) -> C = {x -> g(f(x))}
fun <A, B, C> composeOutcome(mf: (A) -> Outcome<B>, mg: (B) -> Outcome<C>): (A) -> Outcome<C> =
    { a -> mf(a).flatMap(mg) }
