class Event(val point: Point) : Comparable<Event> {
    override fun compareTo(other: Event) = compareByY(this.point, other.point)
}