class Point(val x: Double, val y: Double) : Comparable<Point> {
    override fun compareTo(other: Point): Int =
        if (this.x == other.x) this.y.compareTo(other.y) else this.x.compareTo(other.x)
}

fun compareByY(point1: Point, point2: Point) =
    if (point1.y == point2.y) point1.x.compareTo(point2.x) else point1.y.compareTo(point2.y)

fun midPoint(point1: Point, point2: Point) = Point((point1.x + point2.x) / 2.0, (point1.y + point2.y))