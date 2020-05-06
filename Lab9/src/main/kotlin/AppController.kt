import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.control.Button
import javafx.scene.control.MenuItem
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.text.Font
import java.io.*
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class AppController : Initializable {
    @FXML
    var canvas: Canvas? = null

    @FXML
    var saveAction: MenuItem? = null

    @FXML
    var loadAction: MenuItem? = null


    @FXML
    var input: TextField? = null

    @FXML
    var nextButton: Button? = null
    private var context: GraphicsContext? = null
    private val diameter = 6.0
    private var kApprox = 5
    private val firstRectanglePoint: Point? = null
    private val points: ArrayList<Point> = ArrayList<Point>()
    private var rectangleInput = false
    private val rectangleInputEnded = false
    var debug = false
    var firstPointChosen = -1

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        context = canvas!!.graphicsContext2D
        context?.font = Font(null, 10.0)
        onReset(null)
    }

    @FXML
    fun onReset(e: ActionEvent?) {
        println("Action")
        context!!.fill = Color.BLACK
        rectangleInput = false
        context!!.clearRect(0.0, 0.0, canvas!!.width, canvas!!.height)
    }

    @FXML
    fun onOpenFile(e: ActionEvent?) {
        println("Load")
        points.clear()
        try {
            val reader = BufferedReader(FileReader("int.txt"))
            var s: String?
            while (reader.readLine().also { s = it } != null) {
                val scan = Scanner(s).useLocale(Locale.US)
                val x = scan.nextDouble()
                val y = scan.nextDouble()
                points.add(Point(x, y))
            }
            reader.close()
            redraw()
        } catch (e1: FileNotFoundException) {
            e1.printStackTrace()
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
    }

    @FXML
    fun onSaveAction(e: ActionEvent?) {
        println("Save")
        try {
            val writer = FileWriter("int.txt")
            val fileWriter = BufferedWriter(writer)
            for (point in points) {
                fileWriter.write(java.lang.Double.toString(point.x))
                fileWriter.write(" ")
                fileWriter.write(java.lang.Double.toString(point.y))
                fileWriter.newLine()
            }
            fileWriter.close()
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
    }

    @FXML
    fun canvasClick(event: MouseEvent) {
        val x = event.x
        val y = event.y
        println("Click: $x $y")
        val point = Point(x, y)
        points.add(point)
        drawPoint(point)
    }

    private fun redraw() {
        context!!.clearRect(0.0, 0.0, canvas!!.width, canvas!!.height)
        context!!.stroke = Color.BLACK
        for (p in points) {
            drawPoint(p)
        }
    }

    @FXML
    fun onTextEnter(event: KeyEvent) {
        if (event.code == KeyCode.ENTER) {
            val text = input!!.text
            kApprox = text.toInt()
            nextButtonClick(null)
        }
    }

    @FXML
    fun nextButtonClick(event: MouseEvent?) {
        println("Next button click")
        redraw()
        val voronoi = Voronoi(points)
        for (e in voronoi.getEdgeList()) {
            if (e!!.p1 != null && e.p2 != null) {
                val topY =
                    (if (e.p1!!.y == Double.POSITIVE_INFINITY) -600 else e.p1!!.y)
                context!!.strokeLine(e.p1!!.x, topY as Double, e.p2!!.x, e.p2!!.y)
            }
        }
        context!!.stroke = Color.BLACK
    }

    private fun drawPoint(point: Point) {
        val x: Double = point.x
        val y: Double = point.y
        context!!.fillOval(x - diameter / 2, y - diameter / 2, diameter, diameter)
    }

    private fun drawPoint(point: Point, numb: Int) {
        drawPoint(point)
    }

}