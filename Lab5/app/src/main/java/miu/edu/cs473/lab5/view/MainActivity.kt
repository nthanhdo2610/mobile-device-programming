package miu.edu.cs473.lab5.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import miu.edu.cs473.lab5.databinding.ActivityMainBinding
import miu.edu.cs473.lab5.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var users: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        if (!this::users.isInitialized) {
            users = ArrayList()
        }
        users.add(User("Domenic", "Nguyen", "domenic@gmail.com", "00000"))
        users.add(User("Ruby", "Nguyen", "ruby@gmail.com", "00000"))
        users.add(User("Miley", "Le", "miley@gmail.com", "00000"))
        users.add(User("Say", "Nguyen", "say@gmail.com", "00000"))
        users.add(User("Nick", "Taco", "nick@gmail.com", "00000"))

        //update new user from signup activity
//        val newUser = intent.getSerializableExtra("newUser", User::class.java)
//        if (newUser != null) {
//            users.add(newUser)
//        }
    }

    fun onSignIn(view: View) {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        if (email.isEmpty()) {
            showToast("Email is required")
            return
        }
        if (password.isEmpty()) {
            showToast("Password is required")
            return
        }
        users.forEach { user ->
            if (user.email == email && user.password == password) {
                gotoShoppingActivity(user)
                return
            }
        }
        showToast("Invalid Email or Password")
    }

    fun onSignUp(view: View) {

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun gotoShoppingActivity(user: User) {
        val intent = Intent(this, ShoppingActivity::class.java)
        intent.putExtra("userInfo", user)
        startActivity(intent)
    }
}