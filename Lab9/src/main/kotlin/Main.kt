class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val points = PointGenerator().generatePoints(10)
            val voroniView = VoroniView()
            voroniView.drawPoints(points)
            val fortuneMethod = FortuneMethod(points, Point(0.0, 0.0), Point(11.0, 11.0))
        }
    }
}