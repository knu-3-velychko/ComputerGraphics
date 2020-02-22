class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val graph = Loader("vertex.txt", "edges.txt").readGraph()
            val graphView = GraphView(graph)
            val point = Pair(9.0, 1.0)
            graphView.drawGraph()
            graphView.drawPoint(point)
            val slabDecomposition = SlabDecomposition(graph, point)
            graphView.drawStripe(0.0, 10.0, slabDecomposition.stripes.first)
            graphView.drawStripeEdges(slabDecomposition.stripes.first)
            graphView.drawStripe(0.0, 10.0, slabDecomposition.stripes.second)
            graphView.drawEdge(slabDecomposition.edges.first)
            slabDecomposition.edges.second?.let { graphView.drawEdge(it) }
        }
    }
}