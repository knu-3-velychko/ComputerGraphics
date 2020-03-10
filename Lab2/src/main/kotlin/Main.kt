class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val graph = Loader("vertex.txt", "edges.txt").readGraph()
            val point = GraphNode(10.0, 3.0)
            val graphView = GraphView()

            graphView.drawGraph(graph)
            graphView.drawPoint(point)

            val chainsMethod = ChainsMethod(graph, point)

            graphView.drawChains(chainsMethod.chains)

            for (i in chainsMethod.chains) {
                println("Chain:")
                for (j in i) {
                    println("${j.x} ${j.y}")
                }
                println()
            }

            val chainsView = GraphView()
            chainsView.drawGraph(graph)
            chainsView.drawPoint(point)

            chainsView.drawChain(chainsMethod.chainsBetween.first)
            chainsView.drawChain(chainsMethod.chainsBetween.second)

        }
    }
}