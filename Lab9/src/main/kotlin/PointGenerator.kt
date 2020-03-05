import kotlin.random.Random

class PointGenerator {
    fun generatePoints(number: Int): List<Point> {
        val points = mutableListOf<Point>()
        val random = Random(System.currentTimeMillis())

        for (i in 0 until number) {
            val point = Point(random.nextDouble(10.0), random.nextDouble(10.0))
            points.add(point)
        }

        return points.toList()
    }
}