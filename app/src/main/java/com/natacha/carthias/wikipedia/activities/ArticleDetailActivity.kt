package com.natacha.carthias.wikipedia.activities

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.natacha.carthias.wikipedia.R
import com.natacha.carthias.wikipedia.models.WikiPage
import kotlinx.android.synthetic.main.activity_article_detail.*


/**
 * Created by Natacha Carthias on 24/10/2019
 */

class ArticleDetailActivity : AppCompatActivity() {

    private var currentPage: WikiPage? = null
    var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        webView = findViewById(R.id.article_detail_webview)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)

        //Get page from extras
        val wikiPageJson = intent.getStringExtra("page")
        currentPage = Gson().fromJson<WikiPage>(wikiPageJson, WikiPage::class.java)

        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        webView!!.loadUrl(currentPage!!.fullurl)



    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home) {
            finish()
        }
            return true
    }
}