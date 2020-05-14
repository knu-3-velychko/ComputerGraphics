public class GraphNode(val x: Double, val y: Double, private val edges: MutableList<GraphNode> = mutableListOf()) :
    Comparable<GraphNode> {
    fun addEdge(node: GraphNode) {
        if (edges.find { it == node } == null)
            edges.add(node)
    }

    override fun toString(): String {
        return "(x:$x, y:$y)"
    }

    fun getEdges() = edges.toList()

    override fun compareTo(other: GraphNode): Int {
        return if (this.y != other.y) this.y.compareTo(other.y) else this.x.compareTo(other.x)
    }
}
