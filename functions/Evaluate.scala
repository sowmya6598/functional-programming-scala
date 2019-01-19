package plotter.functions

object Evaluate {

  def evaluate(x: Double, functionString: String): Double = {
    new ExpressionParser(constants = Map("x" -> x)).evaluate(functionString)
  }
}