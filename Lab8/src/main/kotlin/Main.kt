class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val points = Loader("points.txt").readPoints()
            val hullView = HullView()
            hullView.drawPoints(points)
            hullView.drawHull(HullApproximation(points,10,hullView).hull)
        }
    }
}