package com.bright.sunriseset

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bright.sunriseset.databinding.ActivitySharedPrefsBinding
import com.bright.sunriseset.vm.CounterLiveDataViewModel
import com.bright.sunriseset.vm.SharedPrefsViewModel

class SharedPrefsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySharedPrefsBinding
    private lateinit var prefsViewModel: SharedPrefsViewModel
    private lateinit var counterViewModel: CounterLiveDataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivitySharedPrefsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Initialize ViewModel
        initViewModel()
    }

    private fun initViewModel() {
        if (!::prefsViewModel.isInitialized) {
            prefsViewModel = ViewModelProvider(this)[SharedPrefsViewModel::class.java]
            prefsViewModel.initModel(this)
        }

        // Livedata observer implementation
        if (!::counterViewModel.isInitialized) {
            counterViewModel = ViewModelProvider(this)[CounterLiveDataViewModel::class.java]
            counterViewModel.counter.observe(this) {
                binding.textViewCounter.text = it.toString()
            }
        }
    }


    fun savePref(view: View) {
        val text = binding.editTextPref.text.toString()
        prefsViewModel.saveData("my_prefs", text)
        binding.textViewPref.text = prefsViewModel.loadData("my_prefs")
        counterViewModel.updateCounter()
    }
}