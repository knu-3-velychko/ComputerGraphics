class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val points = Loader("points.txt").readPoints()
            val regionView = RegionView()
            regionView.drawPoints(points)
            regionView.drawRegion(Point(1.0, 3.0) to Point(5.0, 6.0))
        }
    }
}