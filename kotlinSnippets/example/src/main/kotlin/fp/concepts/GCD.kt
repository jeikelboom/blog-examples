package fp.concepts

class GCD {

    companion object{
        fun gcd(a: Int, b: Int): Int = if (b==0) a else gcd(b, a % b)

    }
}