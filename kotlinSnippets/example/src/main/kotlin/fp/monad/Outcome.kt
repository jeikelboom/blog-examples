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

// regular functions
fun <A, B, C> composeFunction(f: (A) -> B, g: (B) -> C): (A) -> C = {x -> g(f(x))}
fun <A, B, C> composeOutcomeMonad(mf: (A) -> Outcome<B>, mg: (B) -> Outcome<C>): (A) -> Outcome<C> =
    { a -> mf(a).flatMap(mg) }
fun <A, B> applyFunction(f: (A) -> B, a: A) = f(a)
fun<A, B> applyMonadicFunction(f: (A) -> Outcome<B>, a: Outcome<A>): Outcome<B> = a.flatMap(f)


fun <T> liftValueToOutcome(a: T) = Good(a)
fun <T> pure(a: T) = liftValueToOutcome(a)
fun<A, B> liftToOutcomeFunctor(f: (A) -> B): (Outcome<A>) -> Outcome<B> =  { a -> a.map(f)}


fun <Z> liftToOutcomeMonad(f: () -> Z): () -> Outcome<Z> = {Outcome({f()})}
fun<A, Z> liftToOutcomeMonad(f: (A) -> Z): (A) -> Outcome<Z> =  { a -> Outcome({f(a)})}
fun<A, B, Z> liftToOutcomeMonad(f: (A, B) -> Z): (A, B) -> Outcome<Z> =  { a, b -> Outcome({f(a, b)})}

