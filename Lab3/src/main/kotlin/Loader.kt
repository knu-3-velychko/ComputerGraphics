import java.io.File

class Loader(private val vertexFileName: String, private val edgeFileName: String) {
    private lateinit var vertex: MutableList<GraphNode>
    private var graph = Graph()

    fun readGraph(): Graph {
        readVertex()
        readEdges()
        return graph
    }

    private fun readVertex() {
        vertex = mutableListOf()
        File(vertexFileName).forEachLine {
            val str = it.split(" ")
            val node = GraphNode(str[0].toDouble(), str[1].toDouble())
            vertex.add(node);
            graph.addNode(node)
        }
    }

    private fun readEdges() {
        File(edgeFileName).forEachLine {
            val str = it.split(" ")
            val first = str[0].toInt()
            val second = str[1].toInt()
            graph.addEdge(vertex[first], vertex[second])
        }
    }
}