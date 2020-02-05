public class GraphNode(val x: Int, val y: Int, val edges: MutableList<GraphNode> = mutableListOf()) {
    fun addEdge(node: GraphNode) {
        if (edges.find { it == node } == null)
            edges.add(node)
    }
}