package com.bright.sunriseset

import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.bright.sunriseset.databinding.ActivityVideoPlayerBinding

class VideoPlayerActivity : AppCompatActivity() {

    private val videoTag = "VideoPlayer"
    private val videoPath = "https://www.demonuts.com/Demonuts/smallvideo.mp4"
    private lateinit var mediaController: MediaController
    private lateinit var binding: ActivityVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureVideoView()

    }

    private fun configureVideoView() {
        val videoView = binding.videoView
        videoView.setVideoPath(videoPath)
        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.setOnPreparedListener { mp ->
            mp.isLooping = true
            Log.i(videoTag, "Duration = " + videoView.duration)
        }
        videoView.start()
    }
}