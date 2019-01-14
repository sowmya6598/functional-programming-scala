package plotter

import scala.swing.{Alignment, Label, TextField}
import scala.util.{Failure, Success, Try}

object UIUtils {

  implicit def throwableToErrorMessage(th: Throwable): String = th.getMessage

  // Using generics: reading a value of a specific type from text field (Lecture 9)
  // Control structures: customisable parsing (Lecture 4 )
  def readFromTextField[T](textField: TextField, parse: String => T, defaultValue: T): T =
    Try {
      parse(textField.text)
    } match {
      // Pattern matching on Try outcome (Lecture 8)
      case Success(n) => n
      case Failure(e) => {
        // Implicit Conversion: using 'throwableToErrorMessage' (Lecture 7)
        val errorMessage: String = e
        println("Parsing error: " + errorMessage)
        defaultValue
      }
    }

  def textField(initialValue: String): TextField = new TextField(initialValue) {
    columns = 10
  }

  def label(text: String): Label = new Label(text) {
    horizontalAlignment = Alignment.Left
  }
}