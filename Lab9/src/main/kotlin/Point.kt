import kotlin.math.sqrt

class Point(val x: Double, val y: Double) : Comparable<Point> {
    override fun compareTo(other: Point): Int =
        if (this.x == other.x) this.y.compareTo(other.y) else this.x.compareTo(other.x)

    fun distance(other: Point) =
        sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y))
}

fun compareByY(point1: Point, point2: Point) =
    if (point1.y == point2.y) point1.x.compareTo(point2.x) else point1.y.compareTo(point2.y)

fun midPoint(point1: Point, point2: Point) = Point((point1.x + point2.x) / 2.0, (point1.y + point2.y))

fun rotation(a: Point, b: Point, c: Point) = (c.y - a.y) * (b.x - a.x) - (b.y - a.y) * (c.x - a.x)