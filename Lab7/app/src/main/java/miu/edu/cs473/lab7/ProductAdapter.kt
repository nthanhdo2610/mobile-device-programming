package miu.edu.cs473.lab7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import miu.edu.cs473.lab7.databinding.ProductItemBinding
import java.text.DecimalFormat

class ProductAdapter(private val listener: ProductListener) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var dataSet = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.setData(dataSet[position], listener)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(prodList: ProductList) {
        dataSet.clear()
        dataSet.addAll(prodList.products)
        this.notifyDataSetChanged()
    }

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ProductItemBinding.bind(view)
        private val formatNumber = DecimalFormat("#,###.0")

        fun setData(product: Product, listener: ProductListener) = with(binding) {
            imv.setImageResource(product.image)
            imvLogo.setImageResource(product.icon)
            tvName.text = product.name
            tvPrice.text = "$ ${formatNumber.format(product.price)}"
            tvDesc.text = product.desc

            itemView.setOnClickListener {
                listener.viewProduct(product)
            }

            btnAdd.setOnClickListener {
                listener.selectProduct(product)
            }
        }
    }
}
