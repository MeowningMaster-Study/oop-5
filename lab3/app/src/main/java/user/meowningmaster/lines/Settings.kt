package user.meowningmaster.lines

import android.graphics.Color

object Settings {
    @JvmField
    val ballColors = intArrayOf(
        Color.RED,
        Color.GREEN,
        Color.BLUE,
        Color.WHITE,
        Color.YELLOW,
        Color.BLACK,
        Color.MAGENTA
    )
    @JvmField
    var fieldSize = YX(9, 9)
    const val cellSize = 100
    const val ballRadius = 25
}