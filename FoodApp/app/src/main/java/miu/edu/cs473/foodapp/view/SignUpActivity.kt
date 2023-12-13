package miu.edu.cs473.foodapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import miu.edu.cs473.foodapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        sharedPreferences = getSharedPreferences("user_login", Context.MODE_PRIVATE)
    }

    fun onSignup(view: View) {
        val firstName = binding.etFirstName.text.toString().trim()
        if (firstName.isEmpty()) {
            showToast("First name is required")
            return
        }
        val lastName = binding.etLastName.text.toString().trim()
        if (lastName.isEmpty()) {
            showToast("Last name is required")
            return
        }
        val email = binding.etEmail.text.toString().trim()
        if (email.isEmpty()) {
            showToast("Email is required")
            return
        }
        val password = binding.etPassword.text.toString().trim()
        if (password.isEmpty()) {
            showToast("Password is required")
            return
        }

        showToast("Your account is created successfully")

        // Use apply() for asynchronous save
        sharedPreferences.edit().apply {
            putString("first_name", firstName)
            putString("last_name", lastName)
            putString("email", email)
            putString("password", password)
        }.apply()

        openLoginActivity()
    }

    private fun openLoginActivity() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
