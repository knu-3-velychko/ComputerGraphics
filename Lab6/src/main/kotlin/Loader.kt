import java.io.File

class Loader(private val fileName: String) {
    fun readPoints(): List<Point> {
        val points = mutableListOf<Point>()
        File(fileName).forEachLine {
            val str = it.split(" ")
            val point = Point(str[0].toDouble(), str[1].toDouble())
            points.add(point)
        }
        return points.toList()
    }
}