package user.meowningmaster.lines

import android.app.AlertDialog

object GameOverDialog {
    fun showDialogWindow(context: GameActivity) {
        AlertDialog.Builder(context)
            .setTitle("Game Over!")
            .setMessage("Do you want to start new game?")
            .setPositiveButton(R.string.yes) { _, _ -> context.startNewGame() }
            .setNegativeButton(R.string.no) { _, _ -> }
            .show()
    }
}