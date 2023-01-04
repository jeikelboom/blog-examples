package fp.concepts

fun Int.modulo(divider: Int) = this - divider * this.div(divider)

class GCD {

    companion object{
        fun gcd(a: Int, b: Int): Int = if (b==0) a else gcd(b, a.modulo(b))
    }


}