package com.natacha.carthias.wikipedia.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.natacha.carthias.wikipedia.R
import com.natacha.carthias.wikipedia.WikiApplication
import com.natacha.carthias.wikipedia.managers.WikiManager
import com.natacha.carthias.wikipedia.models.WikiPage
import kotlinx.android.synthetic.main.activity_article_detail.*
import org.jetbrains.anko.toast


/**
 * Created by Natacha Carthias on 24/10/2019
 */

class ArticleDetailActivity : AppCompatActivity() {

    private var wikiManager: WikiManager? = null
    private var currentPage: WikiPage? = null
    var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        wikiManager = (applicationContext as WikiApplication).wikiManager

        webView = findViewById(R.id.article_detail_webview)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)

        //Get page from extras
        val wikiPageJson = intent.getStringExtra("page")
        currentPage = Gson().fromJson<WikiPage>(wikiPageJson, WikiPage::class.java)

        supportActionBar?.title = currentPage?.title

        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        webView!!.loadUrl(currentPage!!.fullurl)

        wikiManager?.addHistory(currentPage!!)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home) {
            finish()
        }
        else if (item!!.itemId == R.id.action_favorite) {

            try {
                if(wikiManager!!.getIsFavorite(currentPage!!.pageid!!)) {
                    wikiManager!!.removeFavorite(currentPage!!.pageid!!)
                    toast("Article removed from favorites")
                }
                else {
                    wikiManager!!.addFavorite(currentPage!!)
                    toast("Article added to favorites")
                }

            }
            catch (ex: Exception) {
                toast("Unable to update this article")
            }

        }
            return true
    }
}