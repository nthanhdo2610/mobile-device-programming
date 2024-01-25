package com.bright.sunriseset

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.bright.sunriseset.databinding.ActivitySensorBinding

class SensorActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivitySensorBinding
    private lateinit var sensorManager: SensorManager
    private lateinit var lightSensor: Sensor
    private lateinit var mp: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySensorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)!!
    }

    // Register your Sensor Manager
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    // Unregister your Sensor Manager
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    // get sensor update and reading
    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_LIGHT) {
            val currentReading = event.values[0]
            // lightmeter is the id of ProgressBar from the layout
            binding.lightmeter.progress = currentReading.toInt()
            binding.reading.text = ("Current Reading: " + (currentReading).toString() + " Lux")
        }
        mp = MediaPlayer.create(applicationContext, R.raw.iphone)
        if (event.values!![0] < 20) { // < 20 use for dark
            try {
                mp.setOnCompletionListener { mp.release() }
                mp.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, i: Int) {
    }
}