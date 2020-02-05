class Graph(private val nodes: MutableList<GraphNode> = mutableListOf()) {
    fun addNode(node: GraphNode) {
        if (!contains(node)) {
            nodes.add(node)
        }
    }

    fun addEdge(node1: GraphNode, node2: GraphNode) {
        addNode(node1)
        addNode(node2)

        node1.addEdge(node2)
        node2.addEdge(node1)
    }

    fun getNodes() = nodes.toList()

    fun contains(node: GraphNode) = nodes.find { it == node } != null

}