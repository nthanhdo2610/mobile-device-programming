package miu.edu.cs473.lab5.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import miu.edu.cs473.lab5.databinding.ActivityRegisterBinding
import miu.edu.cs473.lab5.model.User

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onSignUp(view: View) {
        val firstname = binding.etFirstName.text.toString().trim()
        if (firstname.isEmpty()) {
            showToast("First name is required")
            return
        }
        val lastname = binding.etLastName.text.toString().trim()
        if (lastname.isEmpty()) {
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

        val newUser = User(firstname, lastname, email, password)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("newUser", newUser)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}