class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val points = Loader("vertex.txt").readPoints()
            val hullView = HullView()
            hullView.drawPoints(points)
            QuickHull(points).hull?.toMutableList()?.let { hullView.drawHull(it) }
        }
    }
}