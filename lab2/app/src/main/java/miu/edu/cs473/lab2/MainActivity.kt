package miu.edu.cs473.lab2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import miu.edu.cs473.lab2.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var menus: MutableList<String> = mutableListOf("Hamburger", "Pizza", "Mexican", "American", "Chinese")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvFood.text = menus[0]
    }

    fun onClickAddFood(view: View) {
        val newFood = binding.etFood.text.toString().trim()
        if (newFood.isEmpty()) {
            Toast.makeText(this, "Please input a new food", Toast.LENGTH_SHORT).show()
            return
        }
        menus.add(newFood)
        binding.etFood.text.clear()
        binding.tvFood.text = newFood
    }

    fun onDecide(view: View) {
        val random = Random.nextInt(menus.size)
        binding.tvFood.text = menus[random]
    }

}