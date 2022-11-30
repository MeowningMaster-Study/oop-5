package user.meowningmaster.lines

class PathFinder(private val field: Array<Array<Cell>>, private val from: YX, private val to: YX) {
    private val fieldSize = Settings.fieldSize
    private val visited = Array(fieldSize.y) { Array(fieldSize.x) { false } }

    fun check(): Boolean {
        return step(from)
    }

    private fun step(c: YX): Boolean {
        if (c == to) {
            return true
        }
        if (!c.inField() || visited[c.y][c.x] || (c != from && !field[c.y][c.x].isEmpty())) {
            return false
        }
        visited[c.y][c.x] = true
        return step(YX(c.y - 1, c.x)) ||
                step(YX(c.y + 1, c.x)) ||
                step(YX(c.y, c.x - 1)) ||
                step(YX(c.y, c.x + 1))
    }
}