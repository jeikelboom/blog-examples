package fp.monad

sealed class   Outcome<out RV> {
    abstract fun <A> map(mapper: (RV) -> A): Outcome<A>
    abstract fun <A> flatMap(monadicFunction: (RV) -> Outcome<A>): Outcome<A>
}
class Good<RV>(val returnValue: RV) : Outcome<RV>() {
    override fun <A> map(mapper: (RV) -> A): Outcome<A> = Good(mapper(returnValue))
    override fun <A> flatMap(monadicFunction: (RV) -> Outcome<A>): Outcome<A> = monadicFunction(returnValue)
}
class Bad<RV>(val e: java.lang.Exception): Outcome<RV>() {
    override fun <A> map(mapper: (RV) -> A)= Bad<Nothing>(e)
    override fun <A> flatMap(monadicFunction: (RV) -> Outcome<A>)= Bad<Nothing>(e)
}
class Ugly<RV>(val message:String) : Outcome<RV>() {
    override fun <A> map(mapper: (RV) -> A): Outcome<A> = Ugly<Nothing>(message)
    override fun <A> flatMap(monadicFunction: (RV) -> Outcome<A>): Outcome<A> = Ugly<Nothing>(message)
}

