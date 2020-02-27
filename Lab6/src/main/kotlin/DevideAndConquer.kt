import kotlin.random.Random

class DevideAndConquer(private val points: List<Point>, private val splitNumber: Int) {
    val hull by lazy {
        divide(points.toMutableList())
    }

    private fun divide(list: MutableList<Point>): List<Point> {
        if (list.size <= splitNumber)
            return JarvisMethod(list).hull


        val left = mutableListOf<Point>()
        val right = mutableListOf<Point>()

        left.addAll(list.subList(0, list.size / 2))
        right.addAll(list.subList(list.size / 2, list.size))

        val leftHull = divide(left)
        val rightHull = divide(right)

        return merge(leftHull, rightHull).toList()
    }

    private fun merge(left: List<Point>, right: List<Point>): List<Point> {
        var (l, r) = if (left[0].x <= right[0].x) left to right else right to left
        var mergedHull = concatenate(l, r)

        val randomInsidePoint = getTriangleCentroid(getRandomTriangle(l))

        if (insidePolygon(randomInsidePoint, r)) {
            return GrahamMethod(mergedHull).hull
        }

        val nextRight = findNextRight(randomInsidePoint, r)
        val nextLeft = findNextLeft(randomInsidePoint, r)


        r = if (getRotation(randomInsidePoint, nextLeft, nextRight) >= 0)
            removeChain(nextLeft, nextRight, r)
        else
            removeChain(nextLeft, nextLeft, r)
        mergedHull = concatenate(l, r)

        return GrahamMethod(mergedHull).hull
    }

    private fun concatenate(left: List<Point>, right: List<Point>): MutableList<Point> {
        val mergedHull = mutableListOf<Point>()
        if (left[0].x < right[0].x) {
            mergedHull.addAll(left)
            mergedHull.addAll(right)
        } else {
            mergedHull.addAll(right)
            mergedHull.addAll(left)
        }
        return mergedHull
    }

    private fun getRandomTriangle(points: List<Point>): List<Point> {
        val random = Random(System.currentTimeMillis())
        val triangle = mutableListOf<Point>()
        for (i in 0..2) {
            var id = random.nextInt(points.size)
            while (triangle.contains(points[id])) {
                id = random.nextInt(points.size)
            }
            triangle.add(points[id])
        }

        return triangle.toList()
    }

    private fun getTriangleCentroid(triangle: List<Point>): Point {
        val point = Point(0.0, 0.0)
        for (i in triangle.indices) {
            point.x += triangle[i].x
            point.y += triangle[i].y
        }

        point.x /= 3
        point.y /= 3

        return point
    }

    private fun insidePolygon(point: Point, points: List<Point>): Boolean {
        var count = 0
        for (i in points.indices) {
            var from = points[i]
            var to = points[(i + 1) % points.size]
            if (from.y > to.y) {
                val c = from
                from = to
                to = c
            }
            if (getRotation(point, from, to) >= 0) {
                count++
            }
        }
        return count % 2 != 0
    }

    private fun findNextLeft(point: Point, points: List<Point>): Point {
        var nextLeft = points[0]
        for (i in 1 until points.size) {
            val current = points[i]
            if (getRotation(current, point, nextLeft) > 0) {
                nextLeft = current
            }
        }
        return nextLeft
    }

    private fun findNextRight(point: Point, points: List<Point>): Point {
        var nextRight = points[0]
        for (i in 1 until points.size) {
            val current = points[i]
            if (getRotation(current, point, nextRight) < 0) {
                nextRight = current
            }
        }
        return nextRight
    }

    private fun removeChain(left: Point, right: Point, hull: List<Point>): List<Point> {
        val h = hull.toMutableList()
        var i = 0

        while (i < h.size) {
            val current = h[i]
            if (current != left && current != right && getRotation(left, current, right) > 0) {
                h.removeAt(i)
            } else {
                i++
            }
        }
        return h.toList()
    }

    private fun getRotation(a: Point, b: Point, c: Point) = (c.y - a.y) * (b.x - a.x) - (b.y - a.y) * (c.x - a.x)

}