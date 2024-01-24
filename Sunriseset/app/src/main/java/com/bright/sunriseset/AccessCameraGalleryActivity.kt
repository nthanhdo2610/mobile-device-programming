package com.bright.sunriseset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher

class AccessCameraGalleryActivity : AppCompatActivity() {

    lateinit var startForResultCamera : ActivityResultLauncher<Intent>
    lateinit var startForResultGalley : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access_camera_gallery)
    }
}