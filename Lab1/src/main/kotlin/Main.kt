class Main {
    companion object {
        private val points = arrayOf(
            Point(3.0, 0.0),
            Point(1.0, 3.0),
            Point(3.0, 5.0),
            Point(5.0, 4.0),
            Point(7.0, 2.0),
            Point(5.5, 1.0)
        )

        @JvmStatic
        fun main(args: Array<String>) {
            contains()
            notContainsLeft()
            notContainsRight()
            anchor()
            onLine()
        }

        private fun contains(){

            val polygon = Polygon(points)
            val point = Point(3.0, 4.0)
            if (polygon.contains(point))
                println("Polygon contains the point.")
            else
                println("Polygon doesn't contain the point.")
        }

        private fun notContainsLeft(){
            val polygon = Polygon(points)
            val point = Point(0.0, 4.0)
            if (polygon.contains(point))
                println("Polygon contains the point.")
            else
                println("Polygon doesn't contain the point.")
        }

        private fun notContainsRight(){
            val polygon = Polygon(points)
            val point = Point(7.0, 4.0)
            if (polygon.contains(point))
                println("Polygon contains the point.")
            else
                println("Polygon doesn't contain the point.")
        }

        private fun anchor(){
            val polygon = Polygon(points)
            val point = Point(1.0, 3.0)
            if (polygon.contains(point))
                println("Polygon contains the point.")
            else
                println("Polygon doesn't contain the point.")
        }

        private fun onLine(){
            val polygon = Polygon(points)
            val point = Point(2.0, 1.5)
            if (polygon.contains(point))
                println("Polygon contains the point.")
            else
                println("Polygon doesn't contain the point.")
        }
    }
}