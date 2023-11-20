package miu.edu.cs473.lab6.viewmodel

import android.app.AlertDialog
import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import miu.edu.cs473.lab6.R
import java.text.SimpleDateFormat
import java.util.*

class QuizViewModel(private val context: Context) : ViewModel() {

    val selectedOptionQ1 = ObservableField<Int>()
    val option1Q2 = ObservableField<Boolean>()
    val option2Q2 = ObservableField<Boolean>()
    val option3Q2 = ObservableField<Boolean>()
    val option4Q2 = ObservableField<Boolean>()

    fun onSubmitClicked() {
        val score = calculateScore()
        showResultDialog(score)
    }

    fun onResetClicked() {
        selectedOptionQ1.set(null)
        option1Q2.set(false)
        option2Q2.set(false)
        option3Q2.set(false)
        option4Q2.set(false)
    }

    private fun calculateScore(): Int {
        var score = 0

        if (selectedOptionQ1.get() == R.id.option2Q1) {
            score += 50
        }

        if (option1Q2.get() == true && option2Q2.get() == true && option3Q2.get()
                ?.not() == true && option4Q2.get() == true
        ) {
            score += 50
        }

        return score
    }

    private fun showResultDialog(score: Int) {
        val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val message = "Congratulations! You submitted on $currentDate, and your score is $score%"
        showAlert(message)
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .show()
    }
}
