package com.bright.sunriseset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import com.bright.sunriseset.databinding.ActivityVideoPlayerBinding

class VideoPlayerActivity : AppCompatActivity() {

    private var TAG = "VideoPlayer"
    var mediaController: MediaController? = null
    private lateinit var binding: ActivityVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureVideoView()

    }

    private fun configureVideoView() {
        binding.videoView1.setVideoPath("https://www.demonuts.com/Demonuts/smallvideo.mp4")
        mediaController = MediaController(this)
        mediaController?.setAnchorView(binding.videoView1)
        binding.videoView1.setOnPreparedListener { mp ->
            mp.isLooping = true
            Log.i(TAG, "Duration = " + binding.videoView1.duration)
        }
        binding.videoView1.start()
    }
}