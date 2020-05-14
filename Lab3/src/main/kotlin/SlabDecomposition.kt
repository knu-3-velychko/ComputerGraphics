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

    private var isOutOfGraph = false

    data class Stripe(
        val node: GraphNode,
        val edges: TreeSet<Edge> = TreeSet()
    )

    val stripes by lazy {
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
            stripes.add(Stripe(i, edges.clone() as TreeSet<Edge>))
        }

        when {
            point.second < stripes[0].node.y -> {
                println("Point is out of graph on bottom")
                isOutOfGraph = true
                Pair(stripes[0], null)
            }
            point.second > stripes.last().node.y -> {
                println("Point is out of graph on top")
                isOutOfGraph = true
                Pair(stripes.last(), null)
            }
            else -> {
                findStripes(point, stripes)
            }
        }
    }

    val edges by lazy {
        if (isOutOfGraph)
            null
        else
            findEdges(point, stripes.first.edges.toList())
    }

    //binary search in stripes
    private fun findStripes(point: Pair<Double, Double>, stripes: List<Stripe>): Pair<Stripe, Stripe> {
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

        return Pair(stripes[left], stripes[right])
    }


    private fun findEdges(
        point: Pair<Double, Double>,
        edges: List<Edge>
    ): Pair<Edge, Edge?> {
        var left = 0
        var right = edges.size - 1
        var mid: Int
        var rotation: Double

        if (rotate(edges[0].first, edges[0].second, point) < 0) {
            println("Point is out of graph on right")
            return Pair(edges[0], null)
        }
        if (rotate(edges.last().first, edges.last().second, point) > 0) {
            println("Point is out of graph on left")
            return Pair(edges.last(), null)
        }

        while (right - left > 1) {
            mid = (left + right) / 2
            rotation = rotate(edges[mid].first, edges[mid].second, point)
            if (rotation < 0)
                right = mid
            else if (rotation > 0)
                left = mid
            else {
                println("Point is on edge")
                if (isPoint(point, edges[mid].first))
                    println("Point coincides with vertex ${edges[mid].first} of the edge ${edges[mid]}")
                else if (isPoint(point, edges[mid].second)) {
                    println("Point coincides with vertex ${edges[mid].second} of the edge ${edges[mid]}")
                }
                return Pair(edges[mid], null)
            }
        }

        return Pair(edges[left], edges[right])
    }

    private fun rotate(a: GraphNode, b: GraphNode, c: Pair<Double, Double>) =
        (b.x - a.x) * (c.second - b.y) - (b.y - a.y) * (c.first - b.x)

    private fun isPoint(point: Pair<Double, Double>, node: GraphNode) = point.first == node.x && point.second == node.y

}