import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChartBuilder
import java.awt.Color

class GraphView(private val graph: Graph) {
    val chart = XYChartBuilder().width(600).height(400).xAxisTitle("X").yAxisTitle("Y").build()
    val swingWrapper = SwingWrapper(chart)

    fun drawGraph() {
        graph.getEdges().forEach {
            chart.addSeries(
                "${it.first}  ${it.second}",
                doubleArrayOf(it.first.x, it.second.x),
                doubleArrayOf(it.first.y, it.second.y)
            ).setMarkerColor(Color.black).setLineColor(Color.black)
        }
        swingWrapper.displayChart()
        swingWrapper.repaintChart()
    }

    fun drawPoint(point: Pair<Double, Double>) {
        chart.addSeries(
            "Point: ${point.first}  ${point.second}",
            doubleArrayOf(point.first),
            doubleArrayOf(point.second)
        ).setMarkerColor(Color.RED).setLineColor(Color.RED)
        swingWrapper.repaintChart()
    }

    fun drawPolygon(edge: SlabDecomposition.Edge) {
        chart.addSeries(
            "Edge: ${edge.first}  ${edge.second}",
            doubleArrayOf(edge.first.x, edge.second.x),
            doubleArrayOf(edge.first.y, edge.second.y)
        ).setMarkerColor(Color.RED).setLineColor(Color.RED)
        swingWrapper.repaintChart()
    }
}