package com.bright.sunriseset.vm

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.bright.sunriseset.R

class SharedPrefsViewModel : ViewModel() {

    private lateinit var myPrefs: SharedPreferences
    fun initModel(context: Context) {
        myPrefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    }

    fun saveData(key: String, value: String) {
        val ed = myPrefs.edit()
        if (myPrefs.contains(key)) {
            ed.remove(key)
        }
        ed.putString(key, value)
        ed.apply()
    }

    fun loadData(key: String): String? {
        return myPrefs.getString(key, "")
    }
}