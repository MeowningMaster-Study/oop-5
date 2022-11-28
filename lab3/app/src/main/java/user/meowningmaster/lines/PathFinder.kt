package user.meowningmaster.lines

import java.util.ArrayList

object PathFinder {
    private val wave: MutableList<Point> = ArrayList()
    private const val wall = 9999
    private val dx = intArrayOf(0, 1, 0, -1)
    private val dy = intArrayOf(-1, 0, 1, 0)
    private val fieldSize = Settings.fieldSize
    fun findPath(field: Array<Array<Cell>>, from: YX, to: YX): Boolean {
        val additionalMatrix = initializeAdditionalMatrix(field)
        var ny = from.y + 1
        var nx = from.x + 1
        var oldWave: MutableList<Point> = ArrayList()
        oldWave.add(Point(ny, nx))
        var nstep = 0
        additionalMatrix[ny][nx] = nstep
        while (oldWave.size > 0) {
            nstep++
            wave.clear()
            for (i in oldWave) {
                for (d in 0..3) {
                    ny = i.first + dy[d]
                    nx = i.second + dx[d]
                    if (additionalMatrix[ny][nx] == -1) {
                        if (ny == to.y + 1 && nx == to.x + 1) {
                            wave.clear()
                            oldWave.clear()
                            return true
                        } else {
                            wave.add(Point(ny, nx))
                            additionalMatrix[ny][nx] = nstep
                        }
                    }
                }
            }
            oldWave = ArrayList(wave)
        }
        return false
    }

    private fun initializeAdditionalMatrix(field: Array<Array<Cell>>): Array<IntArray> {
        val additionalField = Array(fieldSize.y + 2) { IntArray(fieldSize.x + 2) }
        run {
            var i = 0
            while (i < fieldSize.y) {
                var j = 0
                while (j < fieldSize.x) {
                    if (field[i][j].isEmpty()) {
                        additionalField[i + 1][j + 1] = -1 //empty
                    } else {
                        additionalField[i + 1][j + 1] = wall //wall
                    }
                    j += 1
                }
                i += 1
            }
        }
        run {
            var i = 0
            while (i < fieldSize.y + 2) {
                additionalField[i][0] = wall
                additionalField[i][fieldSize.x + 1] = wall
                i += 1
            }
        }
        var i = 0
        while (i < fieldSize.x + 2) {
            additionalField[0][i] = wall
            additionalField[fieldSize.y + 1][i] = wall
            i += 1
        }
        return additionalField
    }

    private class Point(var first: Int, var second: Int)
}