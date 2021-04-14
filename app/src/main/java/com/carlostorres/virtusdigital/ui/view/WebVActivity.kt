package com.carlostorres.virtusdigital.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.carlostorres.virtusdigital.R
import com.carlostorres.virtusdigital.ui.utils.toast

class WebVActivity : AppCompatActivity() {

    // Data Received
    private var url: String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_v)

        prepareBackButton()
        onPrepareWebView()
    }

    private fun prepareBackButton() {
        val actionBar = supportActionBar
        actionBar!!.title = intent.extras?.getString("title")
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun onPrepareWebView() {
        val webViewItem: WebView = findViewById(R.id.wvItem)

        webViewItem.webChromeClient = object : WebChromeClient() {}
        webViewItem.webViewClient = object : WebViewClient() {}

        val settings = webViewItem.settings
        settings.javaScriptEnabled = true

        if ( intent.extras?.getString("url").equals("N/A") ) {
            toast(getString(R.string.error_url_na))
            webViewItem.visibility = View.GONE
        } else {
            webViewItem.loadUrl(intent.extras?.getString("url").toString())
        }
    }
}