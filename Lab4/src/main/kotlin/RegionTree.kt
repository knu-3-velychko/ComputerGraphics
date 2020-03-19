class RegionTree(private var points: List<Point>) {
    val root: Node

    init {
        points.sortedBy { it.x }
        root = build(0, points.size)
    }

    private fun build(left: Int, right: Int): Node {
        val node = Node()
        val m = left + (right - left) / 2
        node.mid = points[m].x
        if (points.size == right) {
            node.to = Double.POSITIVE_INFINITY
        } else {
            node.to = points[right - 1].x
        }

        node.from = points[left].x

        node.points = insertPoints(left, right)

        if (m - left > 1)
            node.left = build(left, m)
        if (right - m > 1)
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
        val nodes = searchPointsX(from, to, root)

        val result = mutableListOf<Point>()

        for (i in nodes) {
            result.addAll(searchPointsY(from.y, to.y, i.points))
        }

        return result
    }

    private fun searchPointsX(from: Point, to: Point, node: Node?): List<Node> {
        if (node == null || from.x > to.x || to.x < node.from) {
            return emptyList()
        }
        if (from.x <= node.from && to.x >= node.to) {
            return listOf(node)
        }
        if (from.x >= node.mid) {
            return searchPointsX(from, to, node.right)
        }
        if (to.x < node.mid) {
            return searchPointsX(from, to, node.left)
        }

        val result = mutableListOf<Node>()
        result.addAll(searchPointsX(from, to, node.left))
        result.addAll(searchPointsX(from, to, node.right))

        return result
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
                left = mid + 1
            } else {
                right = mid
            }
        }

        return if (list[right].y >= y) left else -1
    }
}