import java.util.*

class SlabDecomposition(private val graph: Graph, private val point: Pair<Double, Double>) {
    data class Edge(
        val first: GraphNode,
        val second: GraphNode
    ) : Comparable<Edge> {
        override fun compareTo(other: Edge): Int {
            return if (second.x == other.second.x) other.first.x.compareTo(first.x) else other.second.x.compareTo(second.x)
        }
    }

    private data class Stripe(
        val node: GraphNode,
        val edges: TreeSet<Edge> = TreeSet()
    )

    val edge by lazy {
        val nodes = graph.getNodes().sortedWith(compareBy({ it.y }, { it.x }))
        val stripes = mutableListOf<Stripe>()
        val edges: TreeSet<Edge> = TreeSet()

        for (i in nodes) {
            for (j in i.getEdges()) {
                if (i < j) {
                    edges.add(Edge(i, j))
                } else {
                    edges.remove(Edge(j, i))
                }
            }
            println(edges)
            stripes.add(Stripe(i, edges.clone() as TreeSet<Edge>))
        }

        val stripe = findStripe(point, stripes)
        println(stripe)
        findEdge(point, stripe.edges.toList())
    }

    //binary search in stripes
    private fun findStripe(point: Pair<Double, Double>, stripes: List<Stripe>): Stripe {
        var left = 0
        var right = stripes.size - 1
        var mid: Int

        while (right - left > 1) {
            mid = (left + right) / 2
            if (GraphNode(point.first, point.second) < stripes[mid].node) {
                right = mid
            } else {
                left = mid
            }
        }

        return stripes[left]
    }


    private fun findEdge(
        point: Pair<Double, Double>,
        edges: List<Edge>
    ): Edge {
        var left = 0
        var right = edges.size - 1
        var mid = 0

        println("Edges")
        println(edges)

        while (right - left > 1) {
            mid = (left + right) / 2
            if (rotate(edges[mid].first, edges[mid].second, point) < 0)
                right = mid
            else
                left = mid
        }

        return edges[left]
    }

    private fun rotate(a: GraphNode, b: GraphNode, c: Pair<Double, Double>) =
        (b.x - a.x) * (c.second - b.y) - (b.y - a.y) * (c.first - b.x)
}