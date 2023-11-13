package miu.edu.cs473.lab7

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import miu.edu.cs473.lab7.databinding.ActivityMainBinding

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
        // Check if productAdapter is not initialized
        if (!this::productAdapter.isInitialized) {
            productAdapter = ProductAdapter(this)
        }

        // Check if listProducts is not initialized
        if (!this::listProducts.isInitialized) {
            listProducts = ArrayList()
        }

        binding.rvElectronics.adapter = productAdapter
        binding.rvElectronics.layoutManager = LinearLayoutManager(this)

        // Use string resources for product data
        productAdapter.setData(this.createProducts())
    }

    fun onViewCart(view: View) {
        val total = listProducts.size
        showToast(getString(R.string.cart_message, total))
    }

    override fun selectProduct(product: Product) {
        if (!listProducts.contains(product)) {
            listProducts.add(product)
            // Update cart
            updateCart()
        }
        showToast(getString(R.string.product_added_message, product.name))
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
        val buttonText = if (total > 0) {
            getString(R.string.view_cart_button, total)
        } else {
            getString(R.string.view_cart_button_empty)
        }
        binding.btnViewCart.text = buttonText
    }

    private fun createProducts(): ProductList = ProductList(
        listOf(
            Product(
                "iPhone", "iPhone 15 and iPhone 15 Plus. \nDynamic Island. \n48MP Main camera with 2x Telephoto",
                999.99, R.drawable.iphone, R.drawable.icon_iphone
            ),

            Product(
                "iPad", "Apple 2023 iPad Pro 12.9” \n6th Gen 256G, Pencil 2, Magic Keyboard \nElectronics | Color: Grey | Size: xs",
                1199.99, R.drawable.ipad, R.drawable.icon_ipad
            ),

            Product(
                "Macbook", "Apple MacBook Air 15-inch \nM2 Chip with 8-Core CPU",
                1999.99, R.drawable.macbook, R.drawable.icon_macbook
            )
        )
    )
}
