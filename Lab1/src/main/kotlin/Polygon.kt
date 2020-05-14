import java.util.*
import kotlin.math.atan2

class Polygon(private var points: Array<Point>) {
    private val visualizer = Visualizer()

    init {
        setUpArray()
        visualizer.drawPolygon(points, "Polygon")
    }

    private fun setUpArray() {
        points.sortBy { -atan2(-it.x, -it.y) }
        var zeroID = 0
        for (i in points.indices) {
            if (points[i].x < points[zeroID].x || points[i].x == points[zeroID].x && points[i].y < points[zeroID].y)
                zeroID = i
        }
        val list = points.toMutableList()
        Collections.rotate(list, -zeroID)
        points=list.toTypedArray()
    }

    fun contains(point: Point): Boolean {
        visualizer.drawPoint(point, "Initial point")
        visualizer.drawPoint(points[0], "Zero point")
        if (point.x >= points[0].x) {
            return if (rotate(points[0], points[1], point) < 0 || rotate(points[0], points.last(), point) > 0)
                false
            else {
                var l = 1
                var r = points.size - 1
                var mid: Int
                while (r - l > 1) {
                    mid = l + (r - l) / 2
                    if (rotate(points[0], points[mid], point) < 0)
                        r = mid
                    else
                        l = mid
                }

                Thread.sleep(500)
                visualizer.drawLine(points[0], points[l], "Line ${points[0]} - ${points[l]}")
                Thread.sleep(500)
                visualizer.drawLine(points[0], points[r], "Line ${points[0]} - ${points[r]}")
                Thread.sleep(500)
                visualizer.drawLine(points[l], points[r], "Line ${points[l]} - ${points[r]}")
                Thread.sleep(500)
                visualizer.drawLine(points[0], point, "Line zero - point")
                !intersect(points[0], point, points[l], points[r])
            }
        }
        return false
    }

    private fun rotate(a: Point, b: Point, c: Point) = (b.x - a.x) * (c.y - b.y) - (b.y - a.y) * (c.x - b.x)
    private fun intersect(a: Point, b: Point, c: Point, d: Point) =
        rotate(a, b, c) * rotate(a, b, d) <= 0 && rotate(c, d, a) * rotate(c, d, b) < 0
}

