package fp.monad

import java.util.*

object OptDemo {

    fun logOptional(f: Double): Optional<Double> = if (f > 0) Optional.of(Math.log10(f)) else Optional.empty()

    fun logSqrt(f: Double) : Optional<Double> = if (f > 0) Optional.of(Math.sqrt(f)) else Optional.empty()

    val v1 = logOptional(0.01).flatMap {logOptional(it)}
    val v2 = logOptional(10000000000.0).flatMap {logOptional(it)}

    fun <A, B, C> composeFunction(f: (A) -> B, g: (B) -> C): (A) -> C = { g(f(it)) }
    fun <A, B> applyFunction(f: (A) -> B, a: A) = f(a)

    fun <A> liftValue(a: A): Optional<A> = Optional.of(a)
    fun <A, B> liftFunctor(f: (A) -> B): (Optional<A>) -> Optional<B> = { it.map (f)}
    fun <A, B, C> composeFunctor1 (f: (A) -> B, g: (B) -> C): (Optional<A>) -> Optional<C> =
        {x -> x.map ( composeFunction (f, g) ) };
    fun <A, B, C> composeFunctor2 (f: (A) -> B, g: (B) -> C): (Optional<A>) -> Optional<C> =
        composeFunction<Optional<A>, Optional<B>, Optional<C>>(liftFunctor<A, B>(f), liftFunctor<B, C>(g))
    fun <A, B, C> composeFunctor (f: (A) -> B, g: (B) -> C): (Optional<A>) -> Optional<C> =
        {x -> x.map ( f ).map (g)};

    fun<A, B, C> composeMonad(f: (A) -> Optional<B>, g: (B) -> Optional<C>): (A) -> Optional<C> =
        {a ->f(a).flatMap(g)}

    infix fun <A, B> Optional<(A) -> B>.apply1(o: Optional<A>): Optional<B> {
        return this.flatMap { a ->  o.map (a) }
    }


}