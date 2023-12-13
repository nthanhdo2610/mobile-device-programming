package miu.edu.cs473.foodapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import miu.edu.cs473.foodapp.R
import miu.edu.cs473.foodapp.listener.RecipeListener
import miu.edu.cs473.foodapp.model.RecipeModel

class RecipeAdapter(val listener: RecipeListener) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private val dataSet = mutableListOf<RecipeModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(dataSet[position], listener)
    }

    override fun getItemCount(): Int = dataSet.size

    fun setData(list: List<RecipeModel>) {
        dataSet.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    fun addRecipe(recipe: RecipeModel) {
        dataSet.add(0, recipe)
        notifyItemInserted(0)
    }

    fun reloadData() {
        notifyDataSetChanged()
    }

    class RecipeViewHolder(view: View) : ViewHolder(view) {
        private val imv: ImageView = view.findViewById(R.id.imv)
        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvInstructions: TextView = view.findViewById(R.id.tvInstructions)
        private val tvRatings: TextView = view.findViewById(R.id.tvRatings)
        private val tvDetail: TextView = view.findViewById(R.id.tvDetail)

        fun bind(recipe: RecipeModel, listener: RecipeListener) {
            if (recipe.imgUri != null) {
                imv.setImageURI(recipe.imgUri)
            } else {
                if (recipe.image > 0) {
                    imv.setImageResource(recipe.image)
                } else {
                    imv.setImageResource(R.drawable.book)
                }
            }
            tvName.text = recipe.name
            tvInstructions.text = recipe.instructions
            tvRatings.text = "${recipe.rating}-star"
            tvDetail.isGone = recipe.url.isNullOrEmpty()

            // Use a lambda expression instead of an anonymous function
            itemView.setOnClickListener {
                recipe.url?.takeIf { it.isNotEmpty() }?.let {
                    listener.viewRecipe(recipe)
                }
            }
        }
    }

}
