package com.bright.sunriseset

import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.bright.sunriseset.databinding.ActivityVideoPlayerBinding

class VideoPlayerActivity : AppCompatActivity() {

    private val videoPath = "https://www.demonuts.com/Demonuts/smallvideo.mp4"
    private lateinit var mc: MediaController
    private lateinit var binding: ActivityVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureVideoView()

    }

    private fun configureVideoView() {
        val videoView = findViewById<VideoView>(R.id.videoView)
        mc = MediaController(this)
        mc.setAnchorView(videoView)
        videoView.setMediaController(mc)
        videoView.setVideoPath(videoPath)
        videoView.start()
    }
}