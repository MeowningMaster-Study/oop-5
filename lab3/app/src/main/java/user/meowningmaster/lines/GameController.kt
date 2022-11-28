package user.meowningmaster.lines

import java.io.Serializable
import java.util.*

class GameController : Serializable {
    var score = 0
    var gameOver = false
    val nextColors: IntArray = IntArray(ballsPerPhase)
    private var emptyCells: Int = columns * rows
    private lateinit var field: Array<Array<Cell>>

    companion object {
        private const val cellsToDestroy = 5
        private const val ballsPerPhase = 3
        private val random = Random()
        private val rows = Settings.fieldSize.y
        private val columns = Settings.fieldSize.x
    }

    init {
        generateNextColors()
        createCells()
        placePhaseBalls()
    }

    fun getCell(location: YX): Cell {
        return field[location.x][location.y]
    }

    private fun createCells() {
        field = Array(rows){Array(columns){ Cell() } }
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                field[i][j] = Cell()
            }
        }
    }

    private fun generateNextColors() {
        var i = 0
        while (i < ballsPerPhase) {
            val colorIndex = random.nextInt(Settings.ballColors.size)
            nextColors[i] = colorIndex
            i += 1
        }
    }

    private fun placePhaseBalls() {
        if (emptyCells < ballsPerPhase) {
            gameOver = true
            return
        }
        for (i in 0 until ballsPerPhase) {
            while (true) {
                val location = YX(random.nextInt(rows), random.nextInt(columns))
                val cell = getCell(location)
                if (cell.isEmpty()) {
                    cell.color = Settings.ballColors[nextColors[i]]
                    destroyCells(location)
                    break
                }
            }
        }
        emptyCells -= ballsPerPhase
        generateNextColors()
    }

    fun replaceBall(sourceYX: YX, destYX: YX): Boolean {
        val source = getCell(sourceYX)
        val dest = getCell(destYX)
        return if (!source.isEmpty()) {
            if (dest.isEmpty()) {
                val isPath: Boolean = PathFinder.findPath(field, sourceYX, destYX)
                if (isPath) {
                    val tmp = Cell().copy(dest)
                    dest.copy(source)
                    source.copy(tmp)
                    val destroyed = destroyCells(destYX)
                    if (!destroyed) {
                        placePhaseBalls()
                        generateNextColors()
                    }
                    true
                } else {
                    false
                }
            } else {
                false
            }
        } else {
            false
        }
    }

    /**
     * Destroy cells around target if needed
     */
    private fun destroyCells(location: YX): Boolean {
        val row = location.y
        val column = location.x
        val cellFrom = field[row][column]
        //check combination in a row
        var numberOfSameBallsInRow = 1
        var left = 0
        var right = 0
        for (i in column + 1 until columns) { //on the right from main cell
            val current = field[row][i]
            if (current.color == cellFrom.color) {
                numberOfSameBallsInRow++
                right++
            } else break
        }
        for (i in column - 1 downTo 0) { //on the left from main cell
            val current = field[row][i]
            if (current.color == cellFrom.color) {
                numberOfSameBallsInRow++
                left++
            } else break
        }
        if (numberOfSameBallsInRow >= cellsToDestroy) {
            cellFrom.clear()
            for (i in column + 1 until column + right + 1) { //on the right from main cell
                val current = field[row][i]
                current.clear()
            }
            for (i in column - 1 downTo column - 1 - left + 1) { //on the left from main cell
                val current = field[row][i]
                current.clear()
            }
            score += numberOfSameBallsInRow
            emptyCells += numberOfSameBallsInRow
            return true
        }


        //check combination in a column
        var numberOfSameBallsInColumn = 1
        var up = 0
        var down = 0
        for (i in row + 1 until rows) { //on the right from main cell
            val current = field[i][column]
            if (current.color == cellFrom.color) {
                numberOfSameBallsInColumn++
                down++
            } else break
        }
        for (i in row - 1 downTo 0) { //on the left from main cell
            val current = field[i][column]
            if (current.color == cellFrom.color) {
                numberOfSameBallsInColumn++
                up++
            } else break
        }
        if (numberOfSameBallsInColumn >= cellsToDestroy) {
            cellFrom.clear()
            for (i in row + 1 until row + down + 1) { //on the right from main cell
                val current = field[i][column]
                current.clear()
            }
            for (i in row - 1 downTo row - 1 - up + 1) { //on the left from main cell
                val current = field[i][column]
                current.clear()
            }
            score += numberOfSameBallsInColumn
            emptyCells += numberOfSameBallsInColumn
            return true
        }
        return false
    }
}