class GraphNode(var x: Double, var y: Double, private val edges: MutableList<GraphNode> = mutableListOf()) {
    var angle = 0.0

    fun addEdge(node: GraphNode) {
        if (edges.find { it == node } == null)
            edges.add(node)
    }

    override fun toString(): String {
        return "(x:$x, y:$y)"
    }

    fun getEdges() = edges.toList()

    override fun equals(other: Any?): Boolean {
        if (other is GraphNode) {
            if (this.x == other.x && this.y == other.y) {
                return true
            }
        }
        return false
    }
}