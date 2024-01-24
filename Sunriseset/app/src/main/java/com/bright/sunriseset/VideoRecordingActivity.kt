package com.bright.sunriseset

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bright.sunriseset.databinding.ActivityVideoRecordingBinding

class VideoRecordingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoRecordingBinding
    private val captureCode = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityVideoRecordingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recordButton.isEnabled = hasCamera()
    }

    private fun hasCamera(): Boolean {
        return packageManager.hasSystemFeature(
            PackageManager.FEATURE_CAMERA_ANY
        )
    }

    fun startRecording(view: View) {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(intent, captureCode)
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