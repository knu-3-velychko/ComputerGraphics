class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val points = Loader("points.txt").readPoints()
            val regionView = RegionView()
            val region = Point(1.0, 3.0) to Point(5.0, 6.0)
            regionView.drawPoints(points)
            regionView.drawRegion(region)

            val regionTree = RegionTree(points)
            val result = regionTree.searchPoints(region.first, region.second)

            println("Result:")
            if (result.isEmpty()) {
                println("No points in region.")
            }

            for (i in result) {
                println(i)
            }
        }

    }
}