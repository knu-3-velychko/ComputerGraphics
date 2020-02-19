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

        val stripe = findStipe(point, stripes)
    }

    //binary search in stripes
    private fun findStipe(point: Pair<Double, Double>, stripes: List<Stripe>): Stripe {
        var left = 0
        var right = stripes.size - 1
        var mid = 0

        while (left < right) {
            mid = left + (left + right) / 2
            if (point.second < stripes[mid].node.y) {
                right = mid
            } else {
                left = mid
            }
        }

        return stripes[mid]
    }


    private fun findEdge(point: Pair<Double, Double>, stripe: Stripe){
        
    }
}