import kotlin.math.abs

class QuickHull(val points: List<Point>) {
    val hull by lazy {
        if (points.size < 3) {
            null
        }

        var left = 0
        var right = 0

        for (i in points.indices) {
            if (points[i].x < points[left].x)
                left = i
            if (points[i].x > points[right].x)
                right = i
        }

        val result = mutableListOf<Point>()
        quickHull(points.toMutableList(), result, points[left], points[right], 1)
        quickHull(points.toMutableList(), result, points[right], points[left], -1)

        println(result)
        result
    }

    private fun quickHull(
        points: MutableList<Point>,
        hull: MutableList<Point>,
        point1: Point,
        point2: Point,
        side: Int
    ) {
        var ind = -1
        var maxDistance = 0.0

        for (i in points.indices) {
            if (getSide(point1, point2, points[i]) == side) {
                val tmp = getDistance(point1, point2, points[i])
                if (tmp > maxDistance) {
                    ind = i
                    maxDistance = tmp
                }
            }
        }

        if (ind == -1) {
            hull.add(point1)
            hull.add(point2)
            return
        }
        quickHull(points, hull, points[ind], point1, -getSide(points[ind], point1, point2))
        quickHull(points, hull, points[ind], point2, -getSide(points[ind], point2, point1))
    }

    private fun getSide(a: Point, b: Point, c: Point): Int {
        val distance = getDistance(a, b, c)

        if (distance > 0)
            return 1
        if (distance < 0)
            return -1
        return 0
    }

    private fun getDistance(a: Point, b: Point, c: Point) = abs((c.y - a.y) * (b.x - a.x) - (b.y - a.y) * (c.x - a.x))
}