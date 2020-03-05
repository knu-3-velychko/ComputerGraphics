class Edge(var from: Point, var to: Point) {
    val k: Double
    val b: Double

    init {
        k = (to.y - from.y) / (to.x - from.x)
        b = from.y - k * from.x
    }
}

fun intersection(edge1: Edge, edge2: Edge): Point? {
    val x = (edge2.b - edge1.b) / (edge1.k - edge2.k)
    val y = edge1.k * x + edge1.b
    return Point(x, y)
}