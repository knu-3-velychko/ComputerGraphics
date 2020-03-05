import java.lang.RuntimeException

class Arc : Comparable<Arc> {
    var flag: Boolean
    var left: BreakPoint?
    var right: BreakPoint?
    val site: Point
    private val fortuneMethod: FortuneMethod

    constructor(left: BreakPoint?, right: BreakPoint?, fortuneMethod: FortuneMethod, flag: Boolean) {
        this.flag = flag
        this.fortuneMethod = fortuneMethod

        this.left = left
        this.right = right
        if (left == null && right == null)
            throw RuntimeException("No breakpoints.")

        site = left?.right ?: right!!.left
    }

    constructor(point: Point, fortuneMethod: FortuneMethod, flag: Boolean) {
        this.flag = flag
        this.fortuneMethod = fortuneMethod
        this.site = point
        this.left = null
        this.right = null
    }

    fun checkCircle(): Point? {
        if (left == null || right == null) {
            return null
        }
        if (rotation(left!!.left, site, right!!.right) >= 0) {
            return null
        }
        return intersection(left!!.edge, right!!.edge)
    }

    override fun compareTo(other: Arc): Int {
        val sweepLine = fortuneMethod.sweepLine

        if ((this.flag) || (other.flag) || (this.getLeftPoint().x <= other.getRightPoint().x && this.getRightPoint().x >= other.getRightPoint().x || other.getLeftPoint().x <= this.getLeftPoint().x && other.getRightPoint().x >= this.getRightPoint().x))
            return 0

        if (this.getLeftPoint().x == other.getLeftPoint().x && this.getRightPoint().x == other.getRightPoint().x)
            return 0
        if (this.getLeftPoint().x >= other.getRightPoint().x)
            return 1
        if (this.getRightPoint().x <= other.getLeftPoint().x)
            return -1

        return midPoint(
            this.getLeftPoint(),
            this.getRightPoint()
        ).compareTo(midPoint(other.getLeftPoint(), other.getRightPoint()))
    }

    private fun getLeftPoint(): Point {
        if (left != null)
            return left!!.getPoint(fortuneMethod.sweepLine)
        return Point(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)
    }

    private fun getRightPoint(): Point {
        if (left != null)
            return left!!.getPoint(fortuneMethod.sweepLine)
        return Point(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY)
    }
}