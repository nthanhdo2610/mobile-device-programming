package miu.edu.cs473.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import miu.edu.cs473.lab4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onSignIn(view: View) {

    }

    fun onSignUp(view: View) {

    }
}