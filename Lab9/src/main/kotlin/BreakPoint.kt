class BreakPoint(val left: Point, val right: Point, val edge: Edge, val isLeftEdge: Boolean) {
    private var cacheSweepLine: Double = -1.0
    private var cachePoint: Point = Point(-1.0, -1.0)

    fun finish(vert: Point) {
        if (isLeftEdge) {
            this.edge.from = vert;
        } else {
            this.edge.to = vert;
        }
    }

    fun getPoint(sweepLine: Double): Point {
        if (sweepLine == cacheSweepLine) {
            return cachePoint
        }
        cacheSweepLine = sweepLine

        val x: Double
        val y: Double

        if (left.y == right.y) {
            x = (left.x + right.x) / 2.0
            y = ((x - left.x) * (x - left.x) + left.x * left.x - sweepLine * sweepLine) / (2.0 * (left.y - sweepLine))
        } else {
            val (px, py) = if (left.y > right.y) left.x to left.y else right.x to right.y
            //TODO:
        }
        //FIXME:
        return cachePoint
    }
}