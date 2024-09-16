//sorting mechanism
//higher order functions

def addN(n: Int): Int = n + 1

def addNM(f:Int ,x:Int): Int = {
 f+x
}




def applyTwice(x:Int): Int = {
  x + 2
}

val b = addN(12)

def ee(x:Int): Int  = {
  x => x + 2
}
val bthrows = addNM( ee(2) ,2)


val numbers = Array(1,2,3,4,5)
val filteredNumbers = numbers.filterNot(b => b % 2 == 0)


val bn = numbers.map(_=> _ * 2)