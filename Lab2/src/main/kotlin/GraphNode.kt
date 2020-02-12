public class GraphNode(val x: Double, val y: Double, private val edges: MutableList<GraphNode> = mutableListOf()) {
    fun addEdge(node: GraphNode) {
        if (edges.find { it == node } == null)
            edges.add(node)
    }

    override fun toString(): String {
        return "(x:$x, y:$y)"
    }

    fun getEdges() = edges.toList()
}