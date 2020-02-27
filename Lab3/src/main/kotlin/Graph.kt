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

    fun getEdges(): List<Pair<GraphNode, GraphNode>> {
        var edges: MutableList<Pair<GraphNode, GraphNode>> = mutableListOf()
        nodes.toList().forEach { i ->
            i.getEdges().forEach { j ->
                edges.add(Pair(i, j))
            }
        }
        return edges.distinct()
    }

    fun contains(node: GraphNode) = nodes.find { it == node } != null

}