class Graph(val nodes: MutableList<GraphNode> = mutableListOf()) {
    fun addNode(node: GraphNode) {
        if (!contains(node)) {
            nodes.add(node)
        }
    }

    fun addEdge(node1: GraphNode, node2: GraphNode) {
        addNode(node1)
        addNode(node2)

        node1.addEdge(node2)
    }

    val edges by lazy {
        val edges: MutableList<Edge> = mutableListOf()
        nodes.toList().forEach { i ->
            i.getEdges().forEach { j ->
                edges.add(Edge(i, j))
            }
        }
        edges.distinctBy { it.from to it.to }
    }

    val edgesDistinct by lazy {
        val edges: MutableList<Edge> = mutableListOf()
        nodes.toList().forEach { i ->
            i.getEdges().forEach { j ->
                if (i.y == j.y) {
                    if (i.x < j.x) {
                        edges.add(Edge(i, j))
                    } else {
                        edges.add(Edge(j, i))
                    }
                } else {
                    if (i.y < j.y) {
                        edges.add(Edge(i, j))
                    } else {
                        edges.add(Edge(j, i))
                    }
                }
            }
        }
        edges.distinctBy { it.from to it.to }
    }

    fun contains(node: GraphNode) = nodes.find { it == node } != null

}