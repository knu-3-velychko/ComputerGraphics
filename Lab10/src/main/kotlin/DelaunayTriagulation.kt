import kotlin.math.sqrt

class DelaunayTriagulation(val points: List<Point>) {
    private lateinit var recentTriangle: Triangle

    val edges by lazy {
        if (points.size < 3) {
            null
        } else {

        }
    }

    fun addPoint() {

    }

    private fun searchTriangle() {

    }

    private fun insertPoint(point: Point, triangle: List<Point>) {

    }

    private fun flip() {

    }

}