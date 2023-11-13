package miu.edu.cs473.lab7.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import miu.edu.cs473.lab7.R
import miu.edu.cs473.lab7.adapter.ProductAdapter
import miu.edu.cs473.lab7.databinding.ActivityMainBinding
import miu.edu.cs473.lab7.model.Product

class MainActivity : AppCompatActivity(), ProductListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter
    private lateinit var listProducts: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        if (!this::productAdapter.isInitialized) {
            productAdapter = ProductAdapter(this)
        }
        if (!this::listProducts.isInitialized) {
            listProducts = ArrayList()
        }

        binding.rvElectronics.adapter = productAdapter
        binding.rvElectronics.layoutManager = LinearLayoutManager(this)

        productAdapter.setData(Product.createProducts())
    }

    fun onViewCart(view: View) {
        val total = listProducts.size
        showToast("Your cart has $total products")
    }

    override fun selectProduct(product: Product) {
        if (!listProducts.contains(product)) {
            listProducts.add(product)
            //update cart
            updateCart()
        }
        showToast("${product.name} has been added to your cart")
    }

    override fun viewProduct(product: Product) {
        val intent = Intent(this, ProductDetail::class.java)
        intent.putExtra("currentProduct", product)
        startActivity(intent)
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun updateCart() {
        val total = listProducts.size
        if (total > 0) {
            binding.btnViewCart.setText("${resources.getString(R.string.txt_view_cart)}(${total})")
        } else {
            binding.btnViewCart.setText(resources.getString(R.string.txt_view_cart))
        }
    }
}