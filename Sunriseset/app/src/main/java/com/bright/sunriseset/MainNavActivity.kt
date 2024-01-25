package com.bright.sunriseset

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainNavActivity : AppCompatActivity() {
    // Declare Navigation Controller Object
    private lateinit var mainNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        mainNavController = navHostFragment.navController

        // Code to link the navigation controller to the app bar
//        NavigationUI.setupActionBarWithNavController(this, mainNavController)
    }

    // override the onSupportNavigateUp() method to call navigateUp() in the navigation controller
    override fun onSupportNavigateUp(): Boolean {
        return mainNavController.navigateUp()
    }
}
