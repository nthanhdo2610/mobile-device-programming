package com.bright.sunriseset.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterLiveDataViewModel: ViewModel() {

    var counter = MutableLiveData<Int>()

    init{
        counter.value = 0
    }

    fun updateCounter(){
        counter.value = counter.value?.plus(1)
    }

}