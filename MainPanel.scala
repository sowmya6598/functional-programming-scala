package plotter

import java.awt.Insets

import plotter.Functions.supportedFunctions
import plotter.UIUtils._
import scala.swing.ListView.Renderer
import scala.swing.{Button, ComboBox, GridBagPanel}

// Inheritance (Lecture 6)
// Singleton (Lecture 5)
object MainPanel extends GridBagPanel {

  private var graph: Graph = new Graph()
  private val numberOfPointsField = textField("1000")
  private val minXField = textField("1")
  private val maxXField = textField("20")
  private val plotButton = Button("Plot") { repaintGraph() }

  private val functionBox = new ComboBox[Function](supportedFunctions) {
    renderer = Renderer(_.name)
  }

  add(graph.component, constraints(0, 0, gridWidth=4, fill=GridBagPanel.Fill.Horizontal))

  add(label("Function"), constraints(0, 1))
  add(functionBox, constraints(1, 1))
  add(label("Min X"), constraints(2, 1))
  add(minXField, constraints(3, 1))

  add(label("Number of Points"), constraints(0, 2))
  add(numberOfPointsField, constraints(1, 2))
  add(label("Max X"), constraints(2, 2))
  add(maxXField, constraints(3, 2))

  add(plotButton, constraints(3, 3))

  private def repaintGraph(): Unit =
    graph.draw(functionBox.selection.item,
      readFromTextField[Int](numberOfPointsField, _.toInt, 0),
      readFromTextField[Double](minXField, _.toDouble, 0),
      readFromTextField[Double](maxXField, _.toDouble, 0))

  private def constraints(x: Int, y: Int, gridWidth: Int = 1,
                          fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.Horizontal): Constraints = {
    val c = new Constraints
    c.gridx = x
    c.gridy = y
    c.gridwidth = gridWidth
    c.fill = fill
    c.insets = new Insets(2,2,2,2)
    c
  }
}
