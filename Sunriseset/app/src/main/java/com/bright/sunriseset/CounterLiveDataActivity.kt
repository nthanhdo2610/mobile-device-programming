package com.bright.sunriseset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bright.sunriseset.databinding.ActivityCounterLiveDataBinding
import com.bright.sunriseset.databinding.ActivitySharedPrefsBinding
import com.bright.sunriseset.vm.CounterLiveDataViewModel
import com.bright.sunriseset.vm.SharedPrefsViewModel

class CounterLiveDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCounterLiveDataBinding
    private lateinit var counterLiveDataViewModel: CounterLiveDataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityCounterLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Initialize ViewModel
        initViewModel()
    }

    private fun initViewModel() {

        // Livedata observer implementation
        if (!::counterLiveDataViewModel.isInitialized) {
            counterLiveDataViewModel = ViewModelProvider(this)[CounterLiveDataViewModel::class.java]
            counterLiveDataViewModel.counter.observe(this) {
                binding.textViewCounter.text = it.toString()
            }
        }
    }


    fun count(view: View) {
        counterLiveDataViewModel.updateCounter()
    }
}