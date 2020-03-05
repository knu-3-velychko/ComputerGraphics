import java.util.*

class FortuneMethod(val points: List<Point>, val minPoint: Point, val maxPoint: Point) {
    private val edges = mutableListOf<Edge>()
    private val events: TreeSet<Event> = sortedSetOf()
    private val breakPoints = hashSetOf<BreakPoint>()
    private val arcs: TreeMap<Arc, CircleEvent?> = TreeMap()

    var sweepLine = maxPoint.y

    val voroniEdges by lazy {
        for (point in points) {
            events.add(PointEvent(point))
        }

        do {
            var current = events.pollFirst()
            sweepLine = current.point.y
            if (current is PointEvent) {
                handlePointEvent(current as PointEvent)
            } else if (current is CircleEvent) {
                handleCircleEvent(current as CircleEvent)
            }
            VoroniView().drawEdges(edges)
        } while (!events.isEmpty())

        sweepLine = minPoint.y
        for (bp in breakPoints) {
            bp.finish(bp.getPoint(sweepLine))
        }
        edges
    }

    private fun handlePointEvent(event: PointEvent) {
        println("point")
        if (arcs.size == 0) {
            arcs[Arc(event.point, this, false)] = null
            return
        }

        val arcEntryAbove = arcs.floorEntry(Arc(event.point, this, true))
        val arcAbove = arcEntryAbove.key

        //same x of point and arc point
        if (arcs.size == 1 && arcAbove.site.y == event.point.y) {
            val newEdge = Edge(arcAbove.site, event.point)
            newEdge.from = Point((event.point.x + arcAbove.site.x) / 2.0, Double.POSITIVE_INFINITY)

            val newBreakPoint = BreakPoint(arcAbove.site, event.point, newEdge, false)
            breakPoints.add(newBreakPoint)
            edges.add(newEdge)

            val leftArc = Arc(null, newBreakPoint, this, false)
            val rightArc = Arc(newBreakPoint, null, this, false)
            arcs.remove(arcAbove)
            arcs[leftArc] = null
            arcs[rightArc] = null
            return
        }

        val falseCE = arcEntryAbove.value
        if (falseCE != null) {
            events.remove(falseCE)
        }

        val breakLeft = arcAbove.left
        val breakRight = arcAbove.right
        val newEdge = Edge(arcAbove.site, event.point)
        edges.add(newEdge)

        val newBreakLeft = BreakPoint(arcAbove.site, event.point, newEdge, true)
        val newBreakRight = BreakPoint(event.point, arcAbove.site, newEdge, false)
        breakPoints.add(newBreakLeft)
        breakPoints.add(newBreakRight)

        val arcLeft = Arc(breakLeft, newBreakLeft, this, true)
        val center = Arc(newBreakLeft, newBreakRight, this, true)
        val arcRight = Arc(newBreakRight, breakRight, this, true)

        arcs.remove(arcAbove)
        arcs[arcLeft] = null
        arcs[center] = null
        arcs[arcRight] = null

        checkCircleEvent(arcLeft)
        checkCircleEvent(arcRight)
    }

    private fun handleCircleEvent(ce: CircleEvent) {
        println("circle")
        var arcLeft = arcs.lowerKey(ce.arc)
        var arcRight = arcs.higherKey(ce.arc)

        println(arcLeft)
        println(arcRight)

        if (arcRight != null) {
            val falseCe = arcs[arcRight]
            if (falseCe != null) events.remove(falseCe)
            arcs[arcRight] = null
        } else {
            arcRight = ce.arc
        }
        if (arcLeft != null) {
            val falseCe = arcs[arcLeft]
            if (falseCe != null) events.remove(falseCe)
            arcs[arcLeft] = null
        } else {
            arcLeft = ce.arc
        }
        arcs.remove(ce.arc)

        ce.arc.left?.finish(ce.vert)
        ce.arc.right?.finish(ce.vert)

        breakPoints.remove(ce.arc.left)
        breakPoints.remove(ce.arc.right)

        val e = Edge(ce.arc.left!!.left, ce.arc.right!!.right)
        edges.add(e)

        val rotation = rotation(arcLeft.right!!.edge.from, ce.point, arcRight.left!!.edge.from)
        val leftRotation = if (rotation > 0.0) e.k < 0.0 else e.k > 0.0
        if (leftRotation) {
            e.from = ce.vert
        } else {
            e.to = ce.vert
        }

        val newBP = BreakPoint(ce.arc.left!!.left, ce.arc.right!!.right, e, !leftRotation)
        breakPoints.add(newBP)

        arcLeft.right = newBP
        arcRight.left = newBP

        checkCircleEvent(arcLeft)
        checkCircleEvent(arcRight)
    }

    private fun checkCircleEvent(arc: Arc) {
        val circleCenter = arc.checkCircle()
        if (circleCenter != null) {
            val radius = arc.site.distance(circleCenter)
            val circleEventPoint = Point(circleCenter.x, circleCenter.y - radius)
            val ce = CircleEvent(arc, circleEventPoint, circleCenter)
            arcs[arc] = ce
            events.add(ce)
        }
    }
}