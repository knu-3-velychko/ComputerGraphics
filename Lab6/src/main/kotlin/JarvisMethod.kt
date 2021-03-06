class JarvisMethod(private val points: MutableList<Point>) {
    val hull by lazy {
        if (points.size <= 3)
            points
        else {
            val result = mutableListOf<Point>()

            val left = getLeftPoint()
            result.add(left)
            points.remove(left)
            points.add(left)

            var index = findNext(left)
            var point = points[index]
            points.removeAt(index)


            while (point != left) {
                result.add(point)
                index = findNext(point)
                point = points[index]
                points.removeAt(index)
            }

            result
        }
    }

    private fun getLeftPoint(): Point {
        var left = points[0]

        for (i in points) {
            if (i.x < left.x || (i.x == left.x && i.y < left.y)) {
                left = i
            }
        }

        return left
    }

    private fun findNext(point: Point): Int {
        var next = points[0]
        var index = 0
        for (i in points.indices) {
            if (getSide(points[i], next, point) == -1) {
                next = points[i]
                index = i
            }
        }
        return index
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