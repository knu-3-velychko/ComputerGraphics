class Parabola(focus: Point, directrixY: Double) {
    private val a: Double
    private val b: Double
    private val c: Double

    init {
        a = focus.x
        b = focus.y
        c = directrixY
    }
}