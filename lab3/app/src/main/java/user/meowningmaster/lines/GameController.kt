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
        return field[location.y][location.x]
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

    fun moveBall(sourceYX: YX, destYX: YX): Boolean {
        val source = getCell(sourceYX)
        val dest = getCell(destYX)
        return if (!source.isEmpty() && dest.isEmpty() && PathFinder(field, sourceYX, destYX).check()) {
            dest.copy(source)
            source.makeEmpty()
            val destroyed = destroyCells(destYX)
            if (!destroyed) {
                placePhaseBalls()
                generateNextColors()
            }
            true
        } else {
            false
        }
    }

    /**
     * Destroy cells around target if needed
     */
    private fun destroyCells(c: YX): Boolean {
        val cellFrom = getCell(c)

        var ballsInRow = 1
        var left = 0
        var right = 0
        for (i in c.x + 1 until columns) {
            val current = field[c.y][i]
            if (current.color == cellFrom.color) {
                ballsInRow++
                right++
            } else break
        }
        for (i in c.x - 1 downTo 0) {
            val current = field[c.y][i]
            if (current.color == cellFrom.color) {
                ballsInRow++
                left++
            } else break
        }
        if (ballsInRow >= cellsToDestroy) {
            cellFrom.clear()
            for (i in c.x - left until c.x + right + 1) {
                val current = field[c.y][i]
                current.clear()
            }
            score += ballsInRow - 1
            emptyCells += ballsInRow - 1
        }

        var ballsInColumn = 1
        var up = 0
        var down = 0
        for (i in c.y + 1 until rows) {
            val current = field[i][c.x]
            if (current.color == cellFrom.color) {
                ballsInColumn++
                down++
            } else break
        }
        for (i in c.y - 1 downTo 0) {
            val current = field[i][c.x]
            if (current.color == cellFrom.color) {
                ballsInColumn++
                up++
            } else break
        }
        if (ballsInColumn >= cellsToDestroy) {
            cellFrom.clear()
            for (i in c.y - up until c.y + down + 1) {
                val current = field[i][c.x]
                current.clear()
            }
            score += ballsInColumn - 1
            emptyCells += ballsInColumn - 1
        }

        return if (ballsInRow >= cellsToDestroy || ballsInColumn >= cellsToDestroy) {
            score += 1
            emptyCells += 1
            true
        } else false
    }
}