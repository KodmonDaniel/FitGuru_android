package hu.bme.aut.fitguru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import hu.bme.aut.fitguru.databinding.ActivityHealthBinding



//font: Segoe UI Black


class HealthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHealthBinding
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHealthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webView = binding.webview

       // webView.loadUrl("https://www.aut.bme.hu/")
        webView.loadUrl("https://www.bbcgoodfood.com/recipes/category/all-healthy")
    }
}