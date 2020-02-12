import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChartBuilder
import java.awt.Color

class GraphView(private val graph: Graph) {
    fun draw() {
        val chart = XYChartBuilder().width(600).height(400).xAxisTitle("X").yAxisTitle("Y").build()
        graph.getEdges().forEach {
            chart.addSeries(
                "${it.first}  ${it.second}",
                doubleArrayOf(it.first.x, it.second.x),
                doubleArrayOf(it.first.y, it.second.y)
            ).setMarkerColor(Color.black).setLineColor(Color.black)
        }
        val swingWrapper = SwingWrapper(chart)
        swingWrapper.displayChart()

        swingWrapper.repaintChart()
    }
}
