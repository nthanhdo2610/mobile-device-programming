package com.bright.sunriseset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import com.bright.sunriseset.databinding.ActivityAccessCameraGalleryBinding

class AccessCameraGalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccessCameraGalleryBinding

    private lateinit var startForResultCamera: ActivityResultLauncher<Intent>
    private lateinit var startForResultGalley: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccessCameraGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}