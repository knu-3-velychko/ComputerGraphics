import java.util.*
import kotlin.math.abs
import kotlin.math.sign
import kotlin.math.sqrt

class Point(var x: Double, var y: Double) : Comparable<Point?> {

    fun length(): Double {
        return sqrt(x * x + y * y)
    }

    override fun toString(): String {
        return "{" +
                +x +
                ", " + y +
                '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val point = other as Point
        return equal(point.x, x) &&
                equal(point.y, y)
    }

    override fun hashCode(): Int {
        return Objects.hash(x, y)
    }

    companion object {
        private fun intersect(a: Double, b: Double, c: Double, d: Double): Boolean {
            var tmpA = a
            var tmpB = b
            var tmpC = c
            var tmpD = d
            if (tmpA > tmpB) {
                val temp = tmpA
                tmpA = tmpB
                tmpB = temp
            }
            if (tmpC > tmpD) {
                val temp = tmpC
                tmpC = tmpD
                tmpD = temp
            }
            return tmpA.coerceAtLeast(tmpC) <= tmpB.coerceAtMost(tmpD)
        }

        fun area(a: Point, b: Point, c: Point): Double {
            return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x)
        }

        private fun intersection(a: Point, b: Point, c: Point, d: Point): Boolean {
            return (intersect(a.x, b.x, c.x, d.x)
                    && intersect(a.y, b.y, c.y, d.y) && sign(area(a, b, c)) * sign(area(a, b, d)) < 0 && sign(area(c, d, a)) * sign(area(c, d, b)) < 0)
        }

        private fun checkPointInsideSegment(s1: Point, s2: Point, point: Point): Boolean {
            val v1 = Point(point.x - s1.x, point.y - s1.y)
            val v2 = Point(s2.x - point.x, s2.y - point.y)
            return (v1.x * v2.y - v2.x * v1.y).compareTo(0.0) == 0
        }

        fun equal(a: Double, b: Double): Boolean {
            return abs(a - b) < 1e-4
        }

        fun isPointInside(points: ArrayList<Point>, point: Point, minX: Double): Boolean {
            var intersections = 0
            val lineStart = Point(minX, point.y + 0.5)
            for (i in points.indices) {
                val a = points[i]
                if (point.x.compareTo(a.x) == 0 && point.y.compareTo(a.y) == 0) return true
                val b: Point = if (i == points.size - 1) {
                    points[0]
                } else points[i + 1]
                if (checkPointInsideSegment(a, b, point)) return true
                if (intersection(a, b, lineStart, point)) intersections++
            }
            return intersections % 2 == 1
        }
    }

    override fun compareTo(other: Point?): Int {
        if (other == null)
            return 1
        val comp = x.compareTo(other.x)
        return if (comp != 0) comp else y.compareTo(other.y)
    }

}