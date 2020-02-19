class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val graph = Loader("vertex.txt", "edges.txt").readGraph()
            GraphView(graph).draw()
            val slabDecomposition = SlabDecomposition(graph, Pair(3.0, 3.0))
            slabDecomposition.polygon
        }
    }
}