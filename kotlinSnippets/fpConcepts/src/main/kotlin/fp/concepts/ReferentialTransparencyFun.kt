package fp.concepts


object ReferentialTransparencyFun {
    fun aString() = "Hello"
    fun hasOddLenght(s: String) = (s.length).modulo(2) == 1
    fun isHelloOdd() = hasOddLenght(aString())
}

object ReferentialTransparencyVal {
    val aString ="Hello"
    val hasOddLenght = {s:String -> (s.length).modulo(2) == 1}
    val isHelloOdd = hasOddLenght(aString)
}