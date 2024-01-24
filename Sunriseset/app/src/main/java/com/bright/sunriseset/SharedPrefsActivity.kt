package com.bright.sunriseset

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bright.sunriseset.databinding.ActivitySharedPrefsBinding

class SharedPrefsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySharedPrefsBinding
    private lateinit var myPrefsModel: MyPrefsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivitySharedPrefsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Initialize ViewModel
        initViewModel()
    }

    private fun initViewModel() {
        if (!::myPrefsModel.isInitialized) {
            myPrefsModel = ViewModelProvider(this)[MyPrefsViewModel::class.java]
            myPrefsModel.initModel(this@SharedPrefsActivity)
        }
    }


    fun savePref(view: View) {
        val text = binding.editTextPref.text.toString()
        myPrefsModel.saveData("my_prefs", text)
        loadPref(view)
    }

    fun loadPref(view: View) {
        val text = myPrefsModel.loadData("my_prefs");
        binding.textViewPref.text = text
    }
}