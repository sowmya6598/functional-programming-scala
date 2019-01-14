package plotter

// Classes / Case Classes (Lecture 5)
final case class Function(name: String, apply: Double => Double)

// Singleton (Lecture 5)
object Functions {

  val supportedFunctions: Seq[Function] = Seq(
    Function("sin", math.sin),
    Function("cos", math.cos))
}
