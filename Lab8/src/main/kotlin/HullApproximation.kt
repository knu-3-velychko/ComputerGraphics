class HullApproximation(private val points: List<Point>, private val k: Int, private val hullView: HullView) {
    val hull by lazy {
        var minPoint = points[0]
        var maxPoint = points[0]

        for (point in points) {
            if (point.x > maxPoint.x) {
                maxPoint = point
            }
            if (point.x < minPoint.x)
                minPoint = point
        }

        val step = (maxPoint.x - minPoint.x) / k.toDouble()

        for(i in 0..k){
            hullView.drawLine(0.0,10.0,minPoint.x+i*step)
        }

        val minPoints = Array<Point?>(k) { null }
        val maxPoints = Array<Point?>(k) { null }

        for (point in points) {
            for (i in 0 until k) {
                if ((point.x > minPoint.x + i * step || point.x == minPoint.x + i * step) && point.x < minPoint.x + (i + 1) * step) {
                    if (maxPoints[i] == null)
                        maxPoints[i] = point
                    else if (maxPoints[i]!!.y < point.y)
                        maxPoints[i] = point
                    if (minPoints[i] == null)
                        minPoints[i] = point
                    else if (minPoints[i]!!.y > point.y)
                        minPoints[i] = point
                }
            }
        }

        val result = mutableListOf<Point>()

        result.add(minPoint)
        result.add(maxPoint)

        for (i in 0 until k) {
            minPoints[i]?.let { result.add(it) }
            if (minPoints[i] != maxPoints[i]) maxPoints[i]?.let { it1 -> result.add(it1) }
        }

        JarvisMethod(result.toMutableList()).hull
    }
}