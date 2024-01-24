package com.bright.sunriseset

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class MyPrefsViewModel : ViewModel() {

    private lateinit var myPrefs: SharedPreferences

    fun initModel(context: Context) {
        myPrefs = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
    }

    fun saveData(key: String, value: String) {
        val editor = myPrefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun loadData(key: String): String? {
        return myPrefs.getString(key, "")
    }
}