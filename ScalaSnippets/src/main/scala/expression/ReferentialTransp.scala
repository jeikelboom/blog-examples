package expression

object ReferentialTransp {

  class AClass {
    val fortytwo = 42
    def add(left: Int, right: Int): Int = left + right
  }
  class WithVal {
    val fortytwo = 42
    val add = (left: Int, right: Int) => left + right
  }
  class WithDef {
    def fortytwo = 42
    def add(left: Int, right: Int): Int = left + right
  }

}
