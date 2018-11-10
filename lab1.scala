
/*

1) Write a program that generates a sequence of integers given the command line arguments.

eg. > scala s0 12

should output numbers: 0 1 2 3 4 5 6 7 8 9 10 11

> scala s0 12 16

should output 12 13 14 15

*/



def s0(y: Int, x: Int = 0) {
  if (y > x) {
    for (a <- x until y) {
      println(a)
    }
  } else {
    for (a <- y until x) {
      println(a)
    }
  }
}



/*

 2) Write a program that uses List of tuples to store the inventory (like in a flower shop):

 eg. - to describe the name of the flower and how many of them is available on stock

 "rose while star", 12
 "sunflower", 3
 "orchid", 7
 "carnation white", 30
 "carnation red", 25

 Add code to print it nicely (e.g.: fixed size fields using string interpolation).

 rose while star 12
 sunflower 3
 orchid 7
 carnation white 30
 carnation red 25

 Write a generator that filters all flowers for which we need to set order (say have less than 10) and print resulting list again.

*/

var flowerShop : List [(String, Int)] = List(("rose while star", 12),("sunflower", 3),("orchid", 7), ("carnation white", 30), ("carnation red", 25))

println("Inventory")
for (a <- flowerShop) {
  println(a._1 ++ "  " ++ a._2.toString)
}


println("LOW STOCK ALERT !!")
for (a <- flowerShop) {
  if (a._2 < 10)
  {
    println(a._1 ++ " with count " ++ a._2.toString ++ " is low in stock. Please place an order ")
  }
}







