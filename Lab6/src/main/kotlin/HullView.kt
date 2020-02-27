import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChart
import org.knowm.xchart.XYChartBuilder
import org.knowm.xchart.style.markers.SeriesMarkers
import java.awt.Color

class HullView {
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

    fun drawHull(points: MutableList<Point>) {
        val center = Point(0.0, 0.0)
        points.forEach {
            center.x += it.x / points.size
            center.y += it.y / points.size
        }

        points.map { it.angle = Math.atan2(it.y - center.y, it.x - center.x) * 180 / Math.PI }

        points.sortBy { it.angle }

        val x = mutableListOf<Double>()
        val y = mutableListOf<Double>()

        points.forEach {
            x.add(it.x)
            y.add(it.y)
        }
        x.add(points[0].x)
        y.add(points[0].y)

        chart.addSeries(
            "Hull $points", x, y
        ).setMarkerColor(Color.BLACK).setMarker(SeriesMarkers.CIRCLE).setLineColor(Color.BLACK)

        swingWrapper.repaintChart()

    }
}