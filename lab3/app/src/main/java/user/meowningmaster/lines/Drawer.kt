package user.meowningmaster.lines

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint

object Drawer {
    fun getBallBitmap(color: Int): Bitmap {
        val width = Settings.cellSize
        val height = Settings.cellSize
        val radius = Settings.ballRadius
        val paint = Paint()
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        paint.color = color
        val c = Canvas(bmp)
        c.drawCircle((width shr 1).toFloat(), (height shr 1).toFloat(), radius.toFloat(), paint)
        return bmp
    }
}