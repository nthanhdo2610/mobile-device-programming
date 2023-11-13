package miu.edu.cs473.lab7.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import miu.edu.cs473.lab7.R
import miu.edu.cs473.lab7.databinding.ActivityProductDetailBinding
import miu.edu.cs473.lab7.model.Product
import java.text.DecimalFormat


class ProductDetail : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        val product = intent.getParcelableExtra("currentProduct", Product::class.java)
        if (product == null) {
            return
        }
        binding.imv.setImageResource(product.image)
        binding.tvName.setText(product.name)
        binding.tvDesc.setText(product.desc)
        binding.tvPrice.setText("$ ${DecimalFormat("#,###.0").format(product.price)}")
    }

    fun onHome(view: View) {
        finish()
    }
}