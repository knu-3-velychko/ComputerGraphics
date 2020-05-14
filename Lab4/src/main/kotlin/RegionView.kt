import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChartBuilder
import org.knowm.xchart.style.markers.SeriesMarkers
import java.awt.Color

class RegionView {
    private val chart by lazy {
        XYChartBuilder().width(600).height(400).title("Region").xAxisTitle("X").yAxisTitle("Y").build()
    }

    private val sw by lazy {
        val swingWrapper = SwingWrapper(chart)
        swingWrapper.displayChart()
        swingWrapper
    }

    fun drawPoints(points: List<Point>) {
        points.forEach {
            chart.addSeries("Point $it", doubleArrayOf(it.x), doubleArrayOf(it.y)).setMarker(SeriesMarkers.CIRCLE)
                .setMarkerColor(Color.BLACK).setLineColor(
                    Color.BLACK
                )
        }
        sw.repaintChart()
    }

    fun drawRegion(region: Pair<Point, Point>) {
        val x = doubleArrayOf(region.first.x, region.second.x, region.second.x, region.first.x, region.first.x)
        val y = doubleArrayOf(region.first.y, region.first.y, region.second.y, region.second.y, region.first.y)


        chart.addSeries("Region $region", x, y).setMarker(SeriesMarkers.CIRCLE)
            .setMarkerColor(Color.RED).setLineColor(
                Color.RED
            )
        sw.repaintChart()
    }

    fun highlightPoints(points: List<Point>) {
        points.forEach {
            chart.addSeries("Highlight Points $points", doubleArrayOf(it.x), doubleArrayOf(it.y))
                .setMarker(SeriesMarkers.CIRCLE)
                .setMarkerColor(Color.RED).setLineColor(
                    Color.RED
                )
        }
        sw.repaintChart()
    }
}