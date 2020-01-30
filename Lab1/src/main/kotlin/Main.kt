class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val points = arrayOf(
                Point(3.0, 0.0),
                Point(1.0, 3.0),
                Point(3.0, 5.0),
                Point(5.0, 4.0),
                Point(7.0, 2.0),
                Point(5.5, 1.0)
            )
            val polygon = Polygon(points)
            val point = Point(4.0, 2.0)
            println(polygon.contains(point))
        }
    }
}