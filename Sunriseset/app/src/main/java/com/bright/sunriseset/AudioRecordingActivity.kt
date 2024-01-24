package com.bright.sunriseset

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bright.sunriseset.databinding.ActivityAudioRecordingBinding

class AudioRecordingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAudioRecordingBinding
    private val recordCode = 101
    private val storageCode = 102
    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private var audioFilePath: String? = null
    private var isRecording = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudioRecordingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        audioSetup()
    }

    private fun audioSetup() {
        if (!hasMicrophone()) {
            binding.stopButton.isEnabled = false
            binding.playButton.isEnabled = false
            binding.recordButton.isEnabled = false
        } else {
            binding.playButton.isEnabled = false
            binding.stopButton.isEnabled = false
        }
        audioFilePath = Environment.getExternalStorageDirectory()
            .absolutePath + "/myaudio.3gp"
        requestPermission(
            Manifest.permission.RECORD_AUDIO,
            recordCode)
    }
    private fun hasMicrophone(): Boolean {
        val pmanager = this.packageManager
        return pmanager.hasSystemFeature(
            PackageManager.FEATURE_MICROPHONE)
    }
    private fun requestPermission(permissionType: String, requestCode: Int) {
        val permission = ContextCompat.checkSelfPermission(this,
            permissionType)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(permissionType), requestCode
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults : IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            recordCode -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.
                    PERMISSION_GRANTED) {
                    binding.recordButton.isEnabled = false
                    Toast.makeText(this,
                        "Record permission required",
                        Toast.LENGTH_LONG).show()
                } else {
                    requestPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        storageCode)
                }
                return
            }
            storageCode -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.
                    PERMISSION_GRANTED) {
                    binding.recordButton.isEnabled = false
                    Toast.makeText(this,
                        "External Storage permission required",
                        Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }

    fun recordAudio(view: View) {
        isRecording = true
        binding.stopButton.isEnabled = true
        binding.playButton.isEnabled = false
        binding.recordButton.isEnabled = false
        try {
            mediaRecorder = MediaRecorder()
            mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder?.setOutputFormat(
                MediaRecorder.OutputFormat.THREE_GPP)
            mediaRecorder?.setOutputFile(audioFilePath)
            /* Sets the audio encoder to be used for recording.
             If this method is not called, the output file will not contain an audio track.*/
            mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            mediaRecorder?.prepare()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mediaRecorder?.start()
    }

    fun stopAudio(view: View) {
        binding.stopButton.isEnabled = false
        binding.playButton.isEnabled = true
        if (isRecording) {
            binding.recordButton.isEnabled = false
            mediaRecorder?.stop()
            mediaRecorder?.release()
            mediaRecorder = null
            isRecording = false
        } else {
            mediaPlayer?.release()
            mediaPlayer = null
            binding.recordButton.isEnabled = true
        }
    }

    fun playAudio(view: View) {
        binding.playButton.isEnabled = false
        binding.recordButton.isEnabled = false
        binding.stopButton.isEnabled = true
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDataSource(audioFilePath)
        mediaPlayer?.prepare()
        mediaPlayer?.start()
    }
}