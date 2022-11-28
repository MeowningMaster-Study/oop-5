package user.meowningmaster.lines

import java.io.Serializable

class Cell() : Serializable {
    var color = -1

    fun copy(source: Cell): Cell {
        color = source.color
        return this
    }

    fun clear() {
        color = -1
    }

    fun isEmpty(): Boolean {
        return color == -1
    }
}