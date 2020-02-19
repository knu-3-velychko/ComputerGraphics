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

        val stripe = findStripe(point, stripes)
        val edge = findEdge(point, stripe.edges.toList())
    }

    //binary search in stripes
    private fun findStripe(point: Pair<Double, Double>, stripes: List<Stripe>): Stripe {
        var left = 0
        var right = stripes.size - 1
        var mid = 0

        while (right - left > 1) {
            mid = left + (left + right) / 2
            if (point.second < stripes[mid].node.y) {
                right = mid
            } else {
                left = mid
            }
        }

        return stripes[mid]
    }


    private fun findEdge(
        point: Pair<Double, Double>,
        edges: List<Pair<GraphNode, GraphNode>>
    ): Pair<GraphNode, GraphNode> {
        var left = 0
        var right = edges.size - 1
        var mid = 0

        while (right - left > 1) {
            if (rotate(edges[mid].first, edges[mid].second, point) < 0)
                right = mid
            else
                left = mid
        }

        return edges[mid]
    }

    private fun createPolygon(edge: Pair<GraphNode, GraphNode>): List<GraphNode> {
        //TODO
        return listOf()
    }

    private fun rotate(a: GraphNode, b: GraphNode, c: Pair<Double, Double>) =
        (b.x - a.x) * (c.second - b.y) - (b.y - a.y) * (c.first - b.x)
}