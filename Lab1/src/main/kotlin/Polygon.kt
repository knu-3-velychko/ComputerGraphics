import java.util.*

class Polygon(private val points: Array<Point>) {

    private var zeroID:Int=0

    private val zero: Point by lazy {
        for (i in points.indices) {
            if (points[i].x < points[zeroID].x || points[i].x == points[zeroID].x && points[i].y < points[zeroID].y)
                zeroID = i
        }
        points[zeroID]
    }

    private val pointsArray:Array<Point> by lazy {
        val list = points.toMutableList()
        Collections.rotate(list, -zeroID)
        list.removeAt(0)
        list.toTypedArray()
    }


    fun contains(point: Point): Boolean {
        if (point.x >= zero.x) {
            return if (point.x == zero.x && point.y == zero.y)
                true
            else {
                var l = 0
                var r = pointsArray.size - 1
                var mid: Int
                while (r - l > 1) {
                    mid = (l + r) / 2
                    if (rotate(zero, pointsArray[mid], point) < 0)
                        r = mid
                    else
                        l = mid
                }
                !intersect(zero, point, points[l], points[r])
            }
        }
        return false
    }

    private fun rotate(a: Point, b: Point, c: Point) = (b.x - a.x) * (c.y - b.y) - (b.y - a.y) * (c.x - b.x)
    private fun intersect(a: Point, b: Point, c: Point, d: Point) =
        rotate(a, b, c) * rotate(a, b, d) <= 0 && rotate(c, d, a) * rotate(c, d, b) < 0
}

