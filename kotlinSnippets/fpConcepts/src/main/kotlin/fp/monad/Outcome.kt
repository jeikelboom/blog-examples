package fp.monad

sealed class   Outcome<out RV> {
    abstract fun <A> map(mapper: (RV) -> A): Outcome<A>
    abstract fun <A> flatMap(monadicFunction: (RV) -> Outcome<A>): Outcome<A>
}

class Good<V>(val value: V) : Outcome<V>() {
    override fun <A> map(mapper: (V) -> A): Outcome<A> =
        try {
            Good(mapper(value))
        } catch (error: Exception) {
            Bad(error)
        }

    override fun <A> flatMap(monadicFunction: (V) -> Outcome<A>): Outcome<A> =
        try {
            monadicFunction(value)
        } catch (error: Exception) {
            Bad(error)
        }
}

class Bad<RV>(val e: Exception): Outcome<RV>() {
    override fun <A> map(mapper: (RV) -> A)= this as Outcome<A>
    override fun <A> flatMap(monadicFunction: (RV) -> Outcome<A>)= this as Outcome<A>
}

class Ugly<RV>(val message:String) : Outcome<RV>() {
    override fun <A> map(mapper: (RV) -> A): Outcome<A> = this as Outcome<A>
    override fun <A> flatMap(monadicFunction: (RV) -> Outcome<A>): Outcome<A> = this as Outcome<A>
}

fun <T> pure(a: T) = Good(a)

// lift regular functions to functor of monadic
fun<A, B> liftToOutcomeFunctor(f: (A) -> B): (Outcome<A>) -> Outcome<B> =  { a -> a.map(f)}
fun<A, B> liftToOutcomeMonad(f: (A) -> B): (A) -> Outcome<B> =  { a -> Good(a).map(f)}

// regular functions compose and apply
fun <A, B, C> composeFunction(f: (A) -> B, g: (B) -> C): (A) -> C = {x -> g(f(x))}
fun <A, B> applyFunction(f: (A) -> B, a: A) = f(a)

// monadic functions compose and apply
fun <A, B, C> composeOutcomeMonad(mf: (A) -> Outcome<B>, mg: (B) -> Outcome<C>): (A) -> Outcome<C> =
    { a -> mf(a).flatMap(mg) }
fun<A, B> applyMonadicFunction(f: (A) -> Outcome<B>, a: Outcome<A>): Outcome<B> = a.flatMap(f)

