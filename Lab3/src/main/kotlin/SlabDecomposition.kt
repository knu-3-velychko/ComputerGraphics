import java.util.*

class SlabDecomposition(private val graph: Graph, private val point: Pair<Double, Double>) {
    private data class Stripe(
        val node: GraphNode,
        val edges: TreeSet<Pair<GraphNode, GraphNode>> = TreeSet()
    )

    //TODO: Code algorithm
    val polygon by lazy {
        val nodes = graph.getNodes().sortedWith(compareBy({ it.y }, { it.x }))
        val stripes = mutableListOf<Stripe>()
        val edges: TreeSet<Pair<GraphNode, GraphNode>> = TreeSet()


        for (i in nodes) {
            println("${i.x} ${i.y}")
            for (j in i.getEdges()) {
                if ((j.y > i.y) || (j.y == i.y && j.x > i.x)) {
                    edges.add(Pair(i, j))
                } else {
                    edges.remove(Pair(i, j))
                }
            }
            stripes += Stripe(i, edges.clone() as TreeSet<Pair<GraphNode, GraphNode>>)
        }
    }
}