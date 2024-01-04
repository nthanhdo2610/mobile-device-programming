package miu.edu.cs473.lab8.view.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.text.InputFilter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import miu.edu.cs473.lab8.databinding.DialogPlantBinding
import miu.edu.cs473.lab8.listener.DialogListener
import miu.edu.cs473.lab8.model.Plant
import miu.edu.cs473.lab8.model.dateFormatter
import miu.edu.cs473.lab8.util.MinMaxFilter
import java.util.*

class PlantDialog(var listener: DialogListener) : DialogFragment() {

    private lateinit var binding: DialogPlantBinding
    private val calendar = Calendar.getInstance()
    private var selectedDate: Date? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogPlantBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(binding.root)
            .setPositiveButton("Add") { _, _ ->
                addPlant()
            }
            .setNegativeButton("Cancel") { _, _ ->
                dismiss()
            }

        initViews()

        return builder.create()
    }

    private fun initViews() {
        binding.etWateringFrequency.filters = arrayOf<InputFilter>(MinMaxFilter(0, 100))

        binding.tvSelectedDate.setOnClickListener {
            showDatePicker()
        }
    }

    private fun addPlant() {
        val name = binding.etPlantName.text.toString().trim()
        if (name.isEmpty()) {
            showToast("Please input name")
            return
        }
        val type = binding.etPlantType.text.toString().trim()
        if (type.isEmpty()) {
            showToast("Please input type")
            return
        }
        val frequency = binding.etWateringFrequency.text.toString().trim()
        if (frequency.isEmpty()) {
            showToast("Please input watering frequency")
            return
        }
        val wateringFrequency = frequency.toIntOrNull() ?: 1

        if (selectedDate == null) {
            showToast("Please select date")
            return
        }

        val plant = Plant(0, name, type, wateringFrequency, dateFormatter.format(selectedDate))
        listener.onPlantAdded(plant)

        dismiss()
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, monthOfYear, dayOfMonth ->
                selectedDate = getDate(year, monthOfYear, dayOfMonth)
                binding.tvSelectedDate.text = selectedDate?.let { it1 -> dateFormatter.format(it1) }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    private fun getDate(year: Int, month: Int, day: Int): Date {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.time
    }
}
