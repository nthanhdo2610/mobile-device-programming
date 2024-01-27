package com.bright.sunriseset

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.widget.MediaController
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bright.sunriseset.databinding.ActivityVideoRecordingBinding

class VideoRecordingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoRecordingBinding
    private lateinit var mdController: MediaController
    private val captureCode = 101

    private lateinit var camResult: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityVideoRecordingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recordButton.isEnabled = hasCamera()

        mdController = MediaController(this)
        mdController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mdController)

        camResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                binding.videoView.setVideoURI(it.data?.data)
                binding.videoView.start()
            }
        }

        binding.recordButton.setOnClickListener {
            camResult.launch(Intent(MediaStore.ACTION_VIDEO_CAPTURE))
        }
    }

    private fun hasCamera(): Boolean {
        return packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        val videoUri = data?.data
        if (requestCode == captureCode) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    Toast.makeText(
                        this, "Video saved to:\n"
                                + videoUri, Toast.LENGTH_LONG
                    ).show()
                }

                Activity.RESULT_CANCELED -> {
                    Toast.makeText(
                        this, "Video recording cancelled.",
                        Toast.LENGTH_LONG
                    ).show()
                }

                else -> {
                    Toast.makeText(
                        this, "Failed to record video",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

}