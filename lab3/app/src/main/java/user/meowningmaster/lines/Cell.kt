package user.meowningmaster.lines

import java.io.Serializable

var emptyColor = 1

class Cell() : Serializable {
    var color = emptyColor

    fun copy(source: Cell): Cell {
        color = source.color
        return this
    }

    fun clear() {
        color = emptyColor
    }

    fun isEmpty(): Boolean {
        return color == emptyColor
    }

    fun makeEmpty() {
        color = emptyColor
    }
}