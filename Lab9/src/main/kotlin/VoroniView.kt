import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChart
import org.knowm.xchart.XYChartBuilder
import org.knowm.xchart.style.markers.SeriesMarkers
import java.awt.Color

class VoroniView {
    private val chart: XYChart = XYChartBuilder().width(600).height(400).xAxisTitle("X").yAxisTitle("Y").build()
    private val swingWrapper = SwingWrapper(chart)

    fun drawPoints(points: List<Point>) {

        points.forEach {
            chart.addSeries(
                "Point $it",
                doubleArrayOf(it.x),
                doubleArrayOf(it.y)
            ).setMarkerColor(Color.BLACK).setMarker(SeriesMarkers.CIRCLE).setLineColor(Color.BLACK)
        }

        swingWrapper.displayChart()
        swingWrapper.repaintChart()
    }

    fun drawEdges(edges: List<Edge>) {
        edges.forEach {
            chart.addSeries(
                "Edge $it",
                doubleArrayOf(it.from.x, it.to.x),
                doubleArrayOf(it.from.y, it.to.y)
            ).setMarkerColor(Color.BLACK).setMarker(SeriesMarkers.CIRCLE).setLineColor(Color.BLACK)
        }

        swingWrapper.displayChart()
        swingWrapper.repaintChart()
    }
}