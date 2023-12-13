package miu.edu.cs473.foodapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import miu.edu.cs473.foodapp.databinding.ActivityWebviewBinding

class WebviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        val urlString = intent.getStringExtra("currentUrl").orEmpty()
        if (urlString.isNotEmpty()) {
            with(binding.webView.settings) {
                javaScriptEnabled = true
                builtInZoomControls = true
            }
            binding.webView.webViewClient = WebViewClient()
            binding.webView.loadUrl(urlString)
        }
    }

    fun onBack(view: View) {
        finish()
    }
}
