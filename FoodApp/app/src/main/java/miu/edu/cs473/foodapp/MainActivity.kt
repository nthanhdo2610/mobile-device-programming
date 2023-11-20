package miu.edu.cs473.foodapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import miu.edu.cs473.foodapp.databinding.ActivityMainBinding
import miu.edu.cs473.foodapp.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.user = User() // Initialize user object for data binding
    }

    fun onLoginButtonClick() {
        val username = binding.user?.username ?: ""
        val password = binding.user?.password ?: ""

        // Add your login logic here using the entered username and password
        // For example, show a toast message
        // Toast.makeText(this, "Username: $username, Password: $password", Toast.LENGTH_SHORT).show()
    }
}