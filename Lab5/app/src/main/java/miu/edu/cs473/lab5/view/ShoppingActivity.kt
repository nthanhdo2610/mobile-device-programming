package miu.edu.cs473.lab5.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import miu.edu.cs473.lab5.R
import miu.edu.cs473.lab5.databinding.ActivityShoppingBinding
import miu.edu.cs473.lab5.model.User

class ShoppingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        val userInfo = intent.getSerializableExtra( "userInfo", User::class.java)
        binding.tvUserInfo.text = "Welcome ${userInfo?.email}"
    }

    private fun showToast(name: String) {
        Toast.makeText(this, "You have chosen the $name category of shopping", Toast.LENGTH_SHORT).show()
    }
}