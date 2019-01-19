package plotter

import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.{ChartFactory, ChartPanel}
import org.jfree.data.category.DefaultCategoryDataset

import scala.swing.Component

// Classes (Lecture 5)
class Graph {

  private val graphData = new DefaultCategoryDataset()

  val component: Component =
    Component.wrap(new ChartPanel(ChartFactory.createLineChart(
      "Functions", "X", "Y",
      graphData, PlotOrientation.VERTICAL,
      false, true, false)))

  // Functions (Lecture 3)
  def draw(function: Double => Double, numberOfPoints: Int, minX: Double, maxX: Double): Unit = {
    graphData.clear()
    calculatePoints(function, numberOfPoints, minX, maxX) foreach { case (x,y) => graphData.addValue(y, "", x) }
  }

  // Functions (Lecture 3)
  private def calculatePoints(function: Double => Double, numberOfPoints: Int, minX: Double, maxX: Double): Seq[(Int,Double)] = {
    val distance: Double = (maxX - minX) / (numberOfPoints - 1)
    (0 to numberOfPoints) map { i =>
      (i, function(minX + i * distance))
    }
  }
}
