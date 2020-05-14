class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val graph = Loader("vertex.txt", "edges.txt").readGraph()
            val graphView = GraphView(graph)
            val point = Pair(0.0, 3.0)
            graphView.drawGraph()
            graphView.drawPoint(point)
            val slabDecomposition = SlabDecomposition(graph, point)
            graphView.drawStripe(0.0, 10.0, slabDecomposition.stripes.first)
            graphView.drawStripeEdges(slabDecomposition.stripes.first)
            slabDecomposition.stripes.second?.let { graphView.drawStripe(0.0, 10.0, it) }
            slabDecomposition.edges?.first?.let { graphView.drawEdge(it) }
            slabDecomposition.edges?.second?.let { graphView.drawEdge(it) }
        }
    }
}