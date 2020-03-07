class Triangle {
    private val size = 3
    private val array: Array<Point>

    constructor(a: Point, b: Point, c: Point) {
        array = arrayOf(a, b, c)
    }

    constructor(points: Collection<Point>) {
        if (points.size != size) {
            throw IndexOutOfBoundsException()
        }
        val tmp = points.toList()
        array = Array(size) {
            tmp[it]
        }
    }

    operator fun get(index: Int): Point {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException()
        }
        return array[index]
    }
}