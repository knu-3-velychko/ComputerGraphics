data class Node(
    var from: Double = Double.NEGATIVE_INFINITY,
    var to: Double = Double.POSITIVE_INFINITY,
    var mid: Double = 0.0,
    var left: Node? = null,
    var right: Node? = null,
    var points: List<Point> = mutableListOf()
)