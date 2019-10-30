package com.natacha.carthias.wikipedia

import android.app.Application
import com.natacha.carthias.wikipedia.managers.WikiManager
import com.natacha.carthias.wikipedia.provider.ArticleDataProvider
import com.natacha.carthias.wikipedia.repositories.ArticleDatabaseOpenHelper
import com.natacha.carthias.wikipedia.repositories.FavoritesRespository
import com.natacha.carthias.wikipedia.repositories.HistoryRepository

/**
 * Created by Natacha Carthias on 30/10/2019
 */

// Will manage lifecycle of application outside scope of activities and fragments
// Prevents multiple connections to SQLite database at the same time
// Will create single instance within lifecycle of application and reference using context
class WikiApplication: Application() {

    var dbHelper: ArticleDatabaseOpenHelper? = null
    var favoritesRespository: FavoritesRespository? = null
    var historyRepository: HistoryRepository? = null
    var wikiProvider: ArticleDataProvider? = null
    var wikiManager: WikiManager? = null
        private set

    override fun onCreate() {
        super.onCreate()

        dbHelper = ArticleDatabaseOpenHelper(applicationContext)
        favoritesRespository = FavoritesRespository(dbHelper!!)
        historyRepository = HistoryRepository(dbHelper!!)
        wikiProvider = ArticleDataProvider()
        wikiManager = WikiManager(wikiProvider!!, favoritesRespository!!, historyRepository!!)
    }
}