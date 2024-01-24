package com.bright.sunriseset

import android.content.ContentValues.TAG
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bright.sunriseset.databinding.ActivityLocalizationBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class LocalizationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocalizationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityLocalizationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set Locale to Chinese
        setLocale(Locale.SIMPLIFIED_CHINESE)

        // Asynchronously fetch sunrise and sunset times
        fetchAPI()
    }


    //Retrieves a localized time string based on the user's preferred language.
    private fun getLocalizedTime(time: LocalDateTime, context: Context): String {
        // Retrieve the user's preferred language from the device settings
        val userPreferredLanguage = Locale.getDefault().language

        // Create a SimpleDateFormat with the user's preferred language
        val sdf = SimpleDateFormat("hh:mm a", Locale(userPreferredLanguage))

        // Format the LocalDateTime into a string using the specified SimpleDateFormat
        return sdf.format(
            time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        )
    }

    private fun fetchAPI() {
        GlobalScope.launch(Dispatchers.Main) {
            val sunriseDeferred = async(Dispatchers.IO) { fetchTime("sunrise") }
            val sunsetDeferred = async(Dispatchers.IO) { fetchTime("sunset") }

            // Await the results of asynchronous tasks
            val sunriseTime = sunriseDeferred.await()
            val sunsetTime = sunsetDeferred.await()

            // If both sunrise and sunset times are available, localize and display them in Chinese
            if (sunriseTime != null && sunsetTime != null) {
                // Localize sunrise and sunset times
                val localizedSunrise = getLocalizedTime(sunriseTime, this@LocalizationActivity)
                val localizedSunset = getLocalizedTime(sunsetTime, this@LocalizationActivity)

                // Display localized times on TextViews
                binding.textViewSunrise.text =
                    "${getString(R.string.SunriseTime)} $localizedSunrise"
                binding.textViewSunset.text =
                    "${getString(R.string.SunsetTime)} $localizedSunset"
            }
        }
    }

    // Coroutine function to fetch sunrise or sunset time from the Sunrise-Sunset API
    private fun fetchTime(type: String): LocalDateTime? {
        return try {
            val apiUrl =
                URL("https://api.sunrise-sunset.org/json?lat=37.7749&lng=-122.4194&formatted=0")
            val urlConnection: HttpURLConnection = apiUrl.openConnection() as HttpURLConnection
            try {
                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val response = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }

                val jsonResponse = JSONObject(response.toString())
                val timeUTC = jsonResponse.getJSONObject("results").getString(type)
                val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault())
                val dateTime = formatter.parse(timeUTC)
                LocalDateTime.ofInstant(dateTime.toInstant(), ZoneId.systemDefault())
            } finally {
                urlConnection.disconnect()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    //Sets the default locale for the application.
    private fun setLocale(locale: Locale) {
        try {
            // Set the default locale for the application
            Locale.setDefault(locale)

            // Update configuration with the specified locale
            val config = resources.configuration
            config.setLocale(locale)

            // Update display metrics to ensure consistency
            resources.updateConfiguration(config, resources.displayMetrics)

        } catch (e: Exception) {
            // Handle exceptions, if any
            Log.e(TAG, "Error setting locale: ${e.message}")
        }
    }

}