package plotter

import scala.swing._

// Inheritance (Lecture 6)
object SinCosPlotterApp extends SimpleSwingApplication {

  // Polymorphism (Lecture 6)
  override def top = new MainFrame {
    title = "Sin / Cos Plotter"
    contents = MainPanel
  }
}