class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            //val points = Loader("vertex.txt").readPoints()
            val points=PointGenerator().generatePoints(100)
            val hullView = HullView()
            hullView.drawPoints(points)
            hullView.drawHull(JarvisMethod(points.toMutableList()).hull)
        }
    }
}