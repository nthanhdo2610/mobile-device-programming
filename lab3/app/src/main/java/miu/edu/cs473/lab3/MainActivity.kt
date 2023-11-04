package miu.edu.cs473.lab3

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import miu.edu.cs473.lab3.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
//        binding.btnAdd.setBackgroundResource()
        setContentView(binding.root)
    }

    fun onAddRowClicked(view: View) {
        val version = binding.editTextVersion.text.toString()
        val codeName = binding.editTextCodeName.text.toString()

        if (version.isNotEmpty() && codeName.isNotEmpty()) {
            val row = TableRow(this)
            val rowParams =
                TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT)
            rowParams.setMargins(0, 8, 0, 0)
            row.layoutParams = rowParams
            row.setBackgroundColor(Color.parseColor("#FFFF33E4"))
            val params = TableRow.LayoutParams(
                0, // Width set to 0
                TableRow.LayoutParams.WRAP_CONTENT,
                1.0f // Weight set to 1 for equal spacing
            )
            params.setMargins(10, 10, 10, 10)  // Set margins if needed

            val textViewVersion = TextView(this)
            val textViewCodeName = TextView(this)
            textViewVersion.text = version
            textViewCodeName.text = codeName

            textViewVersion.layoutParams = params
            textViewCodeName.layoutParams = params

            row.addView(textViewVersion)
            row.addView(textViewCodeName)
            binding.tableLayout.addView(row)

            // Clear EditText fields after adding a row
            binding.editTextVersion.setText("")
            binding.editTextCodeName.setText("")
        }
    }

}