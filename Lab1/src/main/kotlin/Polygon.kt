import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChartBuilder
import javax.swing.SwingUtilities.invokeLater
import java.util.*
import kotlin.math.atan2

class Polygon(private var points: Array<Point>) {
    companion object Visualizer {
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
            invokeLater {
                chart.addSeries(name, doubleArrayOf(a.x, b.x), doubleArrayOf(a.y, b.y))
                sw.repaintChart()
            }
        }
    }

    init {
        points.sortBy { -atan2(-it.x, -it.y) }
        drawPolygon(points, "Polygon")
    }

    private var zeroID: Int = 0

    private val zero: Point by lazy {
        for (i in points.indices) {
            if (points[i].x < points[zeroID].x || points[i].x == points[zeroID].x && points[i].y < points[zeroID].y)
                zeroID = i
        }
        points[zeroID]
    }

    private val pointsArray: Array<Point> by lazy {
        val list = points.toMutableList()
        Collections.rotate(list, -zeroID)
        list.removeAt(0)
        list.toTypedArray()
    }


    fun contains(point: Point): Boolean {
        drawPoint(point, "Initial point")
        drawPoint(zero, "Zero point")
        if (point.x >= zero.x) {
            return if (rotate(zero, pointsArray[0], point) < 0 || rotate(zero, pointsArray.last(), point) > 0)
                false
            else {
                var l = 0
                var r = pointsArray.size - 1
                var mid: Int
                while (r - l > 1) {
                    mid = l + (r - l) / 2
                    if (rotate(zero, pointsArray[mid], point) < 0)
                        r = mid
                    else
                        l = mid
                }

                Thread.sleep(500)
                drawLine(zero, pointsArray[l], "Line $zero - ${pointsArray[l]}")
                Thread.sleep(500)
                drawLine(zero, pointsArray[r], "Line $zero - ${pointsArray[r]}")
                !intersect(zero, point, points[l], points[r])
            }
        }
        return false
    }

    private fun rotate(a: Point, b: Point, c: Point) = (b.x - a.x) * (c.y - b.y) - (b.y - a.y) * (c.x - b.x)
    private fun intersect(a: Point, b: Point, c: Point, d: Point) =
        rotate(a, b, c) * rotate(a, b, d) <= 0 && rotate(c, d, a) * rotate(c, d, b) < 0
}

