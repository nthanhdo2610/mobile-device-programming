package miu.edu.cs473.foodapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import miu.edu.cs473.foodapp.R
import miu.edu.cs473.foodapp.model.MealSchedulerModel
import java.text.SimpleDateFormat

class SchedulerAdapter : RecyclerView.Adapter<SchedulerAdapter.MealSchedulerViewHolder>() {

    private val dataSet = mutableListOf<MealSchedulerModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealSchedulerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_scheduler, parent, false)
        return MealSchedulerViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealSchedulerViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    fun setData(list: List<MealSchedulerModel>) {
        dataSet.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    fun addMealScheduler(mealScheduler: MealSchedulerModel) {
        dataSet.add(0, mealScheduler)
        notifyItemInserted(0)
    }

    fun reloadData() {
        notifyDataSetChanged()
    }

    class MealSchedulerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvMeals: TextView = view.findViewById(R.id.tvMeal)
        private val dateFormatter = SimpleDateFormat("EEEE, MM/dd/yyyy")

        fun bind(mealScheduler: MealSchedulerModel) {
            tvName.text = dateFormatter.format(mealScheduler.date)
            val mealsText = mealScheduler.meals?.joinToString("\n\n") { item ->
                "${item.name}: ${item.meal}"
            }
            tvMeals.text = mealsText
        }
    }
}
