package com.bright.sunriseset

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bright.sunriseset.databinding.ActivityCounterLiveDataBinding
import com.bright.sunriseset.vm.CounterLiveDataViewModel

class CounterLiveDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCounterLiveDataBinding
    private lateinit var countVM: CounterLiveDataViewModel
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
        if (!::countVM.isInitialized) {
            countVM = ViewModelProvider(this)[CounterLiveDataViewModel::class.java]
            countVM.counter.observe(this) {
                binding.textViewCounter.text = it.toString()
            }
        }
    }


    fun count(view: View) {
        countVM.updateCounter()
    }
}