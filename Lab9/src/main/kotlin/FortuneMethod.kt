class FortuneMethod(val points: List<Point>, val minPoint: Point, val maxPoint: Point) {
    private val edges = mutableListOf<Edge>()
    private val events = sortedSetOf<Event>()
    private val breakPoints = hashSetOf<Point>()
    //val arcs= sortedMapOf()

    val voroniEdges by lazy {
        var sweepLine = maxPoint.y
        for (point in points) {
            events.add(Event(point))
        }

        do {
            var current = events.pollFirst()
            sweepLine = current.point.y
            //TODO: handle point and circle events
        } while (!events.isEmpty())

        edges
    }
}