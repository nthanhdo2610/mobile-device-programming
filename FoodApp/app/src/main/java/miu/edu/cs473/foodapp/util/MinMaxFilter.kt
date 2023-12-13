package miu.edu.cs473.foodapp.util

import android.text.InputFilter
import android.text.Spanned

class MinMaxFilter(private val minValue: Double, private val maxValue: Double) : InputFilter {

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dStart: Int,
        dEnd: Int
    ): CharSequence? {
        try {
            val input: Double = (dest.toString() + source.toString()).toDouble()
            if (isInRange(minValue, maxValue, input)) {
                return null
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        return ""
    }

    // Check if input c is in between min a and max b and
    // returns corresponding boolean
    private fun isInRange(a: Double, b: Double, c: Double): Boolean {
        return if (b > a) c in a..b else c in b..a
    }
}
