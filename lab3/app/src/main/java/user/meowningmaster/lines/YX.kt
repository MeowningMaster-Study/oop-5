package user.meowningmaster.lines

/**
 * Coordinates or size
 */
class YX(var y: Int, var x: Int) {
//    private fun boundBy(bound: YX): YX {
//        y %= bound.y
//        x %= bound.x
//        return this
//    }
//
//    fun boundByField(): YX {
//        return boundBy(Settings.fieldSize)
//    }

    fun inField(): Boolean {
        return y >= 0 && y < Settings.fieldSize.y && x >= 0 && x < Settings.fieldSize.x
    }

    override fun hashCode(): Int {
        var result = y
        result = 31 * result + x
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as YX

        if (y != other.y) return false
        if (x != other.x) return false

        return true
    }
}