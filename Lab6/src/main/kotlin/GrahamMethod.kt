//Graham Algorithm for sorted points set
class GrahamMethod(private val points: MutableList<Point>) {
    val hull by lazy {
        if (points.size <= 3) {
            points.toList()
        } else {
            val start = getLowest()
            val ind = points.indexOf(start)
            points[ind] = points[0]
            points[0] = start

            points.map { it.angle = Math.atan2(it.y - start.y, it.x - start.x) * 180 / Math.PI }

            points.sortBy { it.angle }

            val hull = mutableListOf<Point>()
            hull.add(points[0])
            hull.add(points[1])
            hull.add(points[2])

            for (i in 3 until points.size) {
                while (hull.size >= 2 && getSide(points[i], hull[hull.size - 2], hull[hull.size - 1]) <= 0) {
                    hull.remove(hull.last())
                }
                hull.add(points[i])
            }
            hull
        }
    }

    private fun getLowest(): Point {
        var lowest = points[0]

        for (i in points) {
            if (i.y < lowest.y || (i.y == lowest.y && i.x < lowest.x)) {
                lowest = i
            }
        }

        return lowest
    }

    private fun getSide(a: Point, b: Point, c: Point): Int {
        val rotation = getRotation(a, b, c)

        if (rotation > 0)
            return 1
        if (rotation < 0)
            return -1
        return 0
    }

    private fun getRotation(a: Point, b: Point, c: Point) = (c.y - a.y) * (b.x - a.x) - (b.y - a.y) * (c.x - a.x)

}