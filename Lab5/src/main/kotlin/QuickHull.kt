import kotlin.math.abs

class QuickHull(private val points: List<Point>) {
    val hull by lazy {
        if (points.size < 3) {
            null
        } else {
            var left = 0
            var right = 0

            for (i in points.indices) {
                if (points[i].x < points[left].x)
                    left = i
                if (points[i].x > points[right].x)
                    right = i
            }

            val leftPoint = points[left]
            val rightPoint = points[right]

            val upperPoints = mutableListOf<Point>()
            val lowerPoints = mutableListOf<Point>()

            for (i in points) {
                if (getSide(leftPoint, rightPoint, i) == 1) {
                    upperPoints.add(i)
                } else {
                    lowerPoints.add(i)
                }
            }

            val result = mutableListOf<Point>()

            result.add(leftPoint)
            result.add(rightPoint)

            quickHull(upperPoints, result, points[left], points[right])
            quickHull(lowerPoints, result, points[right], points[left])

            result
        }
    }

    private fun quickHull(
        points: MutableList<Point>,
        hull: MutableList<Point>,
        point1: Point,
        point2: Point
    ) {
        if (points.isEmpty())
            return
        if (points.size == 1) {
            hull.add(points[0])
            return
        }

        val furthest = findFurthestPoint(points, point1, point2)
        hull.add(furthest)

        val leftPoints = mutableListOf<Point>()
        val rightPoints = mutableListOf<Point>()

        for (i in points) {
            if (i == furthest)
                continue
            if (getSide(point1, furthest, i) == 1) {
                leftPoints.add(i)
            } else if (getSide(furthest, point2, i) == 1) {
                rightPoints.add(i)
            }
        }

        quickHull(leftPoints, hull, point1, furthest)
        quickHull(rightPoints, hull, furthest, point2)
    }

    private fun findFurthestPoint(points: MutableList<Point>, point1: Point, point2: Point): Point {
        var maxDistance = getDistance(points[0], point1, point2)
        var maxId = 0

        for (i in points.indices) {
            val distance = getDistance(points[i], point1, point2)
            if (distance > maxDistance) {
                maxDistance = distance
                maxId = i
            } else if (distance == maxDistance) {
                if (points[i].x < points[maxId].x) {
                    maxId = i
                }
            }
        }
        return points[maxId]
    }

    private fun getSide(a: Point, b: Point, c: Point): Int {
        val distance = getRotation(a, b, c)

        if (distance > 0)
            return 1
        if (distance < 0)
            return -1
        return 0
    }

    private fun getDistance(a: Point, b: Point, c: Point) = abs(getRotation(a, b, c))

    private fun getRotation(a: Point, b: Point, c: Point) = (c.y - a.y) * (b.x - a.x) - (b.y - a.y) * (c.x - a.x)
}