package miu.edu.cs473.lab7

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import miu.edu.cs473.lab7.databinding.ActivityProductDetailBinding
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
        product?.apply {
            binding.imv.setImageResource(image)
            binding.tvName.text = name
            binding.tvDesc.text = desc
            binding.tvPrice.text = getString(R.string.price_format, DecimalFormat("#,###.0").format(price))
        }
    }

    fun onHome(view: View) {
        finish()
    }
}
