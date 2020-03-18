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

    fun searchPoints(from: Point, to: Point) {

    }

    private fun searchPointsX():List<Node>{

    }

    private fun searchPointsY():List<Point>{
        
    }
}