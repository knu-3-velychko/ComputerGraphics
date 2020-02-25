class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val points = Loader("vertex.txt").readPoints()
            val hullView = HullView()
            hullView.drawPoints(points)
            hullView.drawHull(QuickHull(points).hull.toMutableList())
        }
    }
}