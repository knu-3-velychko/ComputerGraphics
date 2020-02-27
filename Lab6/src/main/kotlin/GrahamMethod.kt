//Graham Algorithm for sorted points set
class GrahamMethod(private val points: List<Point>) {
    val hull by lazy {
        if (points.size <= 3) {
            points
        } else {
            val p = points.toMutableList()
            var lowest = getLowest()
            val result = mutableListOf(lowest)
            p.remove(lowest)
            p.add(lowest)
            while (true) {
                var j = 0
                var next = p[j]
                j++

                if (next == lowest) {
                    if (p.size == 1)
                        break
                    else {
                        next = p[j]
                        j++
                    }
                }

                for (i in j until p.size) {
                    val current = p[i]
                    if (getSide(lowest, next, current) > 1) {
                        next = current
                    } else {
                        p.remove(p[i])
                    }
                }
                if (next == result[0])
                    break
                result.add(next)
                p.remove(next)
                lowest = next
            }
            result
        }
    }

    private fun getLowest(): Point {
        var lowest = points[0]

        for (i in points) {
            if (i.y < lowest.y || (i.y == lowest.y && i.x < lowest.x)) {
                lowest = i
            }
        }

        return lowest
    }

    private fun getSide(a: Point, b: Point, c: Point): Int {
        val rotation = getRotation(a, b, c)

        if (rotation > 0)
            return 1
        if (rotation < 0)
            return -1
        return 0
    }

    private fun getRotation(a: Point, b: Point, c: Point) = (c.y - a.y) * (b.x - a.x) - (b.y - a.y) * (c.x - a.x)

}