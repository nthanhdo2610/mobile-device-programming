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
    private lateinit var sharedPrefsViewModel: SharedPrefsViewModel
    private lateinit var counterLiveDataViewModel: CounterLiveDataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivitySharedPrefsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Initialize ViewModel
        initViewModel()
    }

    private fun initViewModel() {
        if (!::sharedPrefsViewModel.isInitialized) {
            sharedPrefsViewModel = ViewModelProvider(this)[SharedPrefsViewModel::class.java]
            sharedPrefsViewModel.initModel(this@SharedPrefsActivity)
        }

        // Livedata observer implementation
        if (!::counterLiveDataViewModel.isInitialized) {
            counterLiveDataViewModel = ViewModelProvider(this)[CounterLiveDataViewModel::class.java]
            counterLiveDataViewModel.counter.observe(this) {
                binding.textViewCounter.text = it.toString()
            }
        }
    }


    fun savePref(view: View) {
        val text = binding.editTextPref.text.toString()
        sharedPrefsViewModel.saveData("my_prefs", text)
        binding.textViewPref.text = sharedPrefsViewModel.loadData("my_prefs")
        counterLiveDataViewModel.updateCounter()
    }
}