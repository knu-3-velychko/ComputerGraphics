import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChart
import org.knowm.xchart.XYChartBuilder
import java.awt.Color

class GraphView {
    private val chart: XYChart = XYChartBuilder().width(600).height(400).xAxisTitle("X").yAxisTitle("Y").build()
    private val swingWrapper = SwingWrapper(chart)

    fun drawGraph(graph: Graph) {
        graph.edges.forEach {
            chart.addSeries(
                "${it.from}  ${it.to}",
                doubleArrayOf(it.from.x, it.to.x),
                doubleArrayOf(it.from.y, it.to.y)
            ).setMarkerColor(Color.gray).setLineColor(Color.gray)
        }
        swingWrapper.displayChart()

        swingWrapper.repaintChart()
    }

    fun drawPoint(point: GraphNode) {
        chart.addSeries(
            "Point: $point",
            doubleArrayOf(point.x),
            doubleArrayOf(point.y)
        ).setMarkerColor(Color.green).setLineColor(Color.green)

        swingWrapper.repaintChart()
    }

    fun drawChains(chains: List<List<GraphNode>>) {
        for (i in chains) {
            drawChain(i)
        }
    }

    fun drawChain(chain: List<GraphNode>) {
        if (chain.isEmpty())
            return
        val x = mutableListOf<Double>()
        val y = mutableListOf<Double>()

        for (i in chain) {
            x.add(i.x)
            y.add(i.y)
        }

        chart.addSeries(
            "Chain $chain",
            x,
            y
        )

        swingWrapper.repaintChart()
    }
}
