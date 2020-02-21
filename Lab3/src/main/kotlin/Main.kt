class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val graph = Loader("vertex.txt", "edges.txt").readGraph()
            val graphView = GraphView(graph)
            val point = Pair(3.0, 3.0)
            graphView.drawGraph()
            graphView.drawPoint(point)
            val slabDecomposition = SlabDecomposition(graph, point)
            println(slabDecomposition.edge)
            graphView.drawPolygon(slabDecomposition.edge)
        }
    }
}