package user.meowningmaster.lines

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import user.meowningmaster.lines.Drawer.getBallBitmap
import user.meowningmaster.lines.GameOverDialog.showDialogWindow


class GameActivity : AppCompatActivity() {
    private lateinit var mainView: View
    private lateinit var controller: GameController
    private lateinit var nextColorButtons: Array<ImageButton>
    private var fromYX: YX? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        initValues()
        nextColorButtons = arrayOf(
            mainView.findViewById(R.id.next_color_btn1),
            mainView.findViewById(R.id.next_color_btn2),
            mainView.findViewById(R.id.next_color_btn3)
        )
        createField()
        startNewGame()
        setupStartView()
    }

    private fun setupStartView() {
        updateScore(0)
        val params = LinearLayout.LayoutParams(
            Settings.cellSize,
            Settings.cellSize
        )
        for (b in nextColorButtons) {
            b.layoutParams = params
            b.isEnabled = false
        }
        updateNextColors()
    }

    private fun updateScore(score: Int) {
        val scoreText = mainView.findViewById<View>(R.id.scoreTextView) as TextView
        scoreText.text = String.format("Score : %d", score)
        setContentView(mainView)
    }

    private fun updateNextColors() {
        for (i in nextColorButtons.indices) {
            val b = nextColorButtons[i]
            val colorIndex = controller.nextColors[i]
            b.setImageBitmap(
                getBallBitmap(
                    Settings.ballColors[colorIndex]
                )
            )
        }
        setContentView(mainView)
    }

    private fun initValues() {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mainView = inflater.inflate(R.layout.activity_game, null)
    }

    private fun createField() {
        val fieldLayout = mainView.findViewById<LinearLayout>(R.id.fieldLayout)
        for (i in 0 until Settings.fieldSize.y) {
            val lineLayout = LinearLayout(this)
            lineLayout.layoutParams =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            lineLayout.orientation = LinearLayout.HORIZONTAL
            for (j in 0 until Settings.fieldSize.x) {
                val imageButton = ImageButton(this)
                setUpButton(imageButton, YX(i, j))
                lineLayout.addView(imageButton)
            }
            fieldLayout.addView(lineLayout)
        }
        setContentView(mainView)
    }

    private fun setUpButton(button: ImageButton, yx: YX) {
        button.tag = yx
        val params = LinearLayout.LayoutParams(
            Settings.cellSize,
            Settings.cellSize
        )
        button.layoutParams = params
        button.setOnClickListener(cellsListener)
        val buttonStyle = ResourcesCompat.getDrawable(resources, R.drawable.cell_style, theme)
        button.background = buttonStyle
    }

    private fun updateButtonView(button: ImageButton, bitmap: Bitmap?) {
        button.setImageBitmap(bitmap)
        setContentView(mainView)
    }

    private val cellsListener: View.OnClickListener
        get() = View.OnClickListener { v ->
            val yx = v.tag as YX
            if (fromYX == null) {
                val cell = controller.getCell(yx)
                if (!cell.isEmpty()) fromYX = yx
            } else {
                val replaced = controller.moveBall(fromYX!!, yx)
                fromYX = null
                syncState()
                if (!replaced) {
                    Toast.makeText(baseContext, R.string.no_way, Toast.LENGTH_SHORT).show()
                }
            }
        }

    private fun syncState() {
        updateField()
        updateNextColors()
        val score = controller.score
        updateScore(score)
        if (controller.gameOver) {
            endGameHandler()
        }
    }

    private fun endGameHandler() {
        showDialogWindow(this)
    }

    private fun updateField() {
        for (i in 0 until Settings.fieldSize.y) {
            for (j in 0 until Settings.fieldSize.x) {
                val yx = YX(i, j)
                val imageButton = mainView.findViewWithTag<ImageButton>(yx)
                val cell = controller.getCell(yx)
                val newButtonBitmap: Bitmap? = if (!cell.isEmpty()) {
                    getBallBitmap(cell.color)
                } else {
                    null
                }
                updateButtonView(imageButton, newButtonBitmap)
            }
        }
        setContentView(mainView)
    }

    fun startNewGame() {
        controller = GameController()
        updateNextColors()
        updateScore(0)
        updateField()
    }
}
