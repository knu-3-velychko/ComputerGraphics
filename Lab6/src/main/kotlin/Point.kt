import kotlin.math.sqrt

data class Point(
    var x: Double,
    var y: Double,
    var angle: Double = 0.0
)

fun Point.abs() = sqrt(x * x + y * y)

operator fun Point.times(other: Point) = x * other.x + y * other.y

operator fun Point.minus(other: Point) = Point(x - other.x, y - other.y)

fun cos(a: Point, b: Point, c: Point): Double {
    val ba = b - a
    val bc = b - c

    return (ba * bc) / (ba.abs() * b.abs())
}