class RegionTree(private var points: List<Point>) {
    val root: Node

    init {
        points.sortedBy { it.x }
        root = build(0, points.size)
    }

    private fun build(left: Int, right: Int): Node {
        val node = Node()
        val m = (left + right) / 2
        node.mid = points[m].x
        if (points.size == right) {
            node.to = Double.POSITIVE_INFINITY
        } else {
            node.to = points[right - 1].x
        }

        node.from = points[left].x

        node.points = insertPoints(left, right)

        node.left = build(left, m)
        node.right = build(m, right)

        return node
    }

    private fun insertPoints(left: Int, right: Int): List<Point> {
        val list = mutableListOf<Point>()
        for (i in left until right) {
            list.add(points[i])
        }
        list.sortBy { it.y }
        return list.toList()
    }

    fun searchPoints(from: Point, to: Point): List<Point> {
        val nodes = searchPointsX()

        val result = mutableListOf<Point>()

        for (i in nodes) {
            result.addAll(searchPointsY(from.y, to.y, i.points))
        }

        return result
    }

    private fun searchPointsX(): List<Node> {
        
    }

    private fun searchPointsY(minY: Double, maxY: Double, list: List<Point>): List<Point> {
        val right = less(maxY, list)
        if (right == -1) {
            return emptyList()
        }
        val left = greater(minY, list)
        if (left == -1) {
            return emptyList()
        }

        val result = mutableListOf<Point>()

        for (i in left..right) {
            result.add(list[i])
        }

        return result
    }

    private fun less(y: Double, list: List<Point>): Int {
        var left = 0
        var right = list.size - 1
        var mid: Int

        while (left < right) {
            mid = left + (right - left) / 2

            if (list[mid].y > y) {
                right = mid
            } else {
                left = mid + 1
            }
        }

        return if (list[left].y <= y) left else -1
    }

    private fun greater(y: Double, list: List<Point>): Int {
        var left = 0
        var right = list.size - 1
        var mid: Int

        while (left < right) {
            mid = left + (right - left) / 2

            if (list[mid].y < y) {
                left = mid
            } else {
                right = mid - 1
            }
        }

        return if (list[right].y >= y) left else -1
    }
}