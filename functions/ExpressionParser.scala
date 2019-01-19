package plotter.functions

import scala.math._
import scala.util.parsing.combinator._
import scala.util.Random

class ExpressionParser(val constants: Map[String,Double] = Map(), val userFcts: Map[String,String => Double] = Map(), random: Random = new Random) extends JavaTokenParsers {

  private val unaryOperations: Map[String,Double => Double] = Map(
    "sin" -> (sin(_)),
    "cos" -> (cos(_)))

  private val binaryOperations: Map[String,(Double,Double) => Double] = Map(
    "+" -> (_+_),
    "-" -> (_-_),
    "*" -> (_*_),
    "/" -> (_/_)
  )

  def evaluate(formula: String) = parseAll(expression,formula).get

  private def fold(d: Double, l: List[~[String,Double]]) = l.foldLeft(d){ case (d1,op~d2) => binaryOperations(op)(d1,d2) }
  private implicit def map2Parser[V](m: Map[String,V]) = m.keys.map(_ ^^ (identity)).reduceLeft(_ | _)

  private def constant:    Parser[Double] = constants ^^ (constants(_))
  private def expression:  Parser[Double] = signum~term~rep(("+"|"-")~term) ^^ { case s~t~l => fold(s * t,l) }
  private def signum:      Parser[Double] = opt("+" | "-") ^^ { case None => 1; case Some("+") => 1; case Some("-") => -1 }

  private def term:     Parser[Double] = longExp~rep(("*"|"/")~longExp) ^^ { case d~l => fold(d,l) }
  private def longExp:  Parser[Double] = shortExp~rep("^"~shortExp) ^^ { case d~l => fold(d,l) }
  private def shortExp: Parser[Double] = float | signum~(constant | unaryExp | "("~>expression<~")") ^^ { case s~x => s * x }
  private def float:    Parser[Double] = floatingPointNumber ^^ (_.toDouble)
  private def unaryExp: Parser[Double] = unaryOperations~"("~expression~")" ^^ { case op~_~d~_ => unaryOperations(op)(d) }
}
