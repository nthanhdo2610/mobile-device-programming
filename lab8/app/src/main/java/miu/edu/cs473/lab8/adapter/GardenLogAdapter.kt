package miu.edu.cs473.lab8.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import miu.edu.cs473.lab8.R
import miu.edu.cs473.lab8.listener.GardenLogListener
import miu.edu.cs473.lab8.model.Plant

class GardenLogAdapter(private val listener: GardenLogListener) : RecyclerView.Adapter<GardenLogAdapter.PlantViewHolder>() {
    private var dataSet = mutableListOf<Plant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_garden_log, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.setData(dataSet[position], listener)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(list: List<Plant>) {
        dataSet.addAll(list)
        notifyDataSetChanged()
    }

    fun addPlant(plant: Plant) {
        dataSet.add(0, plant)
        notifyItemInserted(0)
    }

    class PlantViewHolder(view: View) : ViewHolder(view) {
        private val tvName: TextView = view.findViewById(R.id.tvName)

        fun setData(plant: Plant, listener: GardenLogListener) {
            tvName.text = plant.name

            itemView.setOnClickListener {
                listener.viewGardenLog(plant)
            }
        }
    }
}
