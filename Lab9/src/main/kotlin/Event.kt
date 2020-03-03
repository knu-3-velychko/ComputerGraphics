abstract class Event(val point: Point) : Comparable<Event> {
    override fun compareTo(other: Event) = compareByY(this.point, other.point)
}

class PointEvent(point: Point) : Event(point)

class CircleEvent(point: Point, val vert: Point) : Event(point)