class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val graph = Loader("vertex.txt", "edges.txt").readGraph()
            val graphView = GraphView()
            graphView.drawGraph(graph)

            val chainsMethod = ChainsMethod(graph)

            for (i in chainsMethod.chains) {
                println("Chain: ")
                for (j in i) {
                    println(j)
                }
                println()
            }

            graphView.drawChains(chainsMethod.chains)
            println("finished")
        }
    }
}