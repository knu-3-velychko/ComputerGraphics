import kotlin.math.atan2

class JarvisMethod(private val points: MutableList<Point>) {
    val hull by lazy {
        if (points.size <= 3)
            points
        else {

            println(cos(Point(7.0, 4.8), Point(3.5, 6.5), Point(2.0, 5.0)))
            println(cos(Point(7.0, 4.8), Point(3.5, 6.5), Point(1.0, 3.0)))

            val result = mutableListOf<Point>()

            val start = getLeftPoint()
            result.add(start)

            var index = findFirst(start)
            var point1 = start
            var point2 = points[index]
            points.removeAt(index)

            println(point2)

            result.add(point1)

            while (point2 != start) {
                result.add(point2)
                index = findNext(point1, point2)
                point1 = point2
                point2 = points[index]
                points.removeAt(index)
            }

            result
        }
    }

    private fun getLeftPoint(): Point {
        var point = points[0]

        for (i in points) {
            if (i.x < point.x || (i.x == point.x && i.y < point.y)) {
                point = i
            }
        }

        return point
    }

    private fun findFirst(point: Point): Int {
        var next = points[0]
        var index = 0
        for (i in points.indices) {
            if (atan2(points[i].y - point.y, points[i].x - point.x) > atan2(next.y - point.y, next.x - point.x)) {
                next = points[i]
                index = i
            }
        }
        return index
    }

    private fun findNext(point1: Point, point2: Point): Int {
        var next = points[0]
        var index = 0
        for (i in points.indices) {
            if ((cos(point1, point2, points[i])) > cos(point1, point2, next) || (cos(point1, point2, points[i]) == cos(
                    point1,
                    point2,
                    next
                ) && points[i].x > next.x)
            ) {
                next = points[i]
                index = i
            }
        }
        return index
    }
}