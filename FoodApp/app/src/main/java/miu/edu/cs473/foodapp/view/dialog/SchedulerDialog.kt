package miu.edu.cs473.foodapp.view.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import miu.edu.cs473.foodapp.databinding.DialogSchedulerBinding
import miu.edu.cs473.foodapp.listener.DialogListener
import miu.edu.cs473.foodapp.model.MealSchedulerModel
import miu.edu.cs473.foodapp.model.SchedulerModel
import java.text.SimpleDateFormat
import java.util.*

class SchedulerDialog(private val listener: DialogListener) : DialogFragment() {

    private lateinit var binding: DialogSchedulerBinding
    private val calendar = Calendar.getInstance()

    @SuppressLint("SimpleDateFormat")
    private val dateFormatter = SimpleDateFormat("EEEE, MM/dd/yyyy")
    private var selectedDate: Date? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogSchedulerBinding.inflate(layoutInflater)
        val dialog = activity?.let {
            AlertDialog.Builder(it)
                .setView(binding.root)
                .create()
        } ?: throw IllegalStateException("Error!!!")

        initViews()

        return dialog
    }

    private fun initViews() {
        binding.tvSelectedDate.setOnClickListener {
            showDatePicker()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnAdd.setOnClickListener {
            addMealScheduler()
        }
    }

    private fun addMealScheduler() {
        if (selectedDate == null) {
            showToast("Please select a day")
            return
        }

        val breakfast = binding.etBreakfast.text.toString().trim()
        val lunch = binding.etLunch.text.toString().trim()
        val dinner = binding.etDinner.text.toString().trim()

        if (breakfast.isEmpty() && lunch.isEmpty() && dinner.isEmpty()) {
            showToast("Please input at least one meal for a day")
            return
        }

        val meals = mutableListOf<SchedulerModel>().apply {
            if (breakfast.isNotEmpty()) add(SchedulerModel("Breakfast", breakfast))
            if (lunch.isNotEmpty()) add(SchedulerModel("Lunch", lunch))
            if (dinner.isNotEmpty()) add(SchedulerModel("Dinner", dinner))
        }

        val scheduler = MealSchedulerModel(selectedDate ?: Date(), meals)
        listener.addMealScheduler(scheduler)

        dismiss()
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, monthOfYear, dayOfMonth ->
                // Display selected date in textbox
                selectedDate = getDate(year, monthOfYear, dayOfMonth)
                binding.tvSelectedDate.text = selectedDate?.let { dateFormatter.format(it) }?.toEditable()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

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

