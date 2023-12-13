package miu.edu.cs473.foodapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import miu.edu.cs473.foodapp.R
import miu.edu.cs473.foodapp.listener.BlogListener
import miu.edu.cs473.foodapp.model.BlogModel

class BlogAdapter(private val listener: BlogListener) : RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {

    private val dataSet = mutableListOf<BlogModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_blog, parent, false)
        return BlogViewHolder(view)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    fun setData(list: List<BlogModel>) {
        dataSet.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    fun addBlog(blog: BlogModel) {
        dataSet.add(0, blog)
        notifyItemInserted(0)
    }

    inner class BlogViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvDesc: TextView = view.findViewById(R.id.tvDesc)

        fun bind(blog: BlogModel) {
            tvName.text = blog.name
            tvDesc.text = blog.desc.orEmpty()

            itemView.setOnClickListener {
                blog.url.takeIf { it.isNotEmpty() }?.let {
                    listener.viewBlog(blog)
                }
            }
        }
    }
}
