data class Node(
    val range: Pair<Double, Double>,
    val left: Node,
    val right: Node,
    val points:List<Point>
)