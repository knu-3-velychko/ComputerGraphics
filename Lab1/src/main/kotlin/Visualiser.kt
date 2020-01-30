import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChartBuilder
import javax.swing.SwingUtilities

class Visualizer {
    private val chart by lazy {
        XYChartBuilder().width(600).height(400).title("Area Chart").xAxisTitle("X").yAxisTitle("Y").build()
    }

    private val sw by lazy {
        val swingWrapper = SwingWrapper(chart)
        swingWrapper.displayChart()
        swingWrapper
    }

    fun drawPolygon(points: Array<Point>, name: String) {
        val x = List(points.size + 1) { i -> if (i == points.size) points[0].x else points[i].x }
        val y = List(points.size + 1) { i -> if (i == points.size) points[0].y else points[i].y }

        chart.addSeries(name, x, y)
        sw.repaintChart()
    }

    fun drawPoint(point: Point, name: String) {
        chart.addSeries(name, doubleArrayOf(point.x), doubleArrayOf(point.y))
        sw.repaintChart()
    }

    fun drawLine(a: Point, b: Point, name: String) {
        SwingUtilities.invokeLater {
            chart.addSeries(name, doubleArrayOf(a.x, b.x), doubleArrayOf(a.y, b.y))
            sw.repaintChart()
        }
    }
}