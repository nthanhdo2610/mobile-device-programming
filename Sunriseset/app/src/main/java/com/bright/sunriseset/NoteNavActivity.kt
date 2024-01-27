package com.bright.sunriseset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class NoteNavActivity : AppCompatActivity() {

    // Declare Navigation Controller Object
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_nav)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
    }

    // override the onSupportNavigateUp() method to call navigateUp() in the navigation controller
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}