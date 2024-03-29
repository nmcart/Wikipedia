package com.natacha.carthias.wikipedia.repositories

import com.google.gson.Gson
import com.natacha.carthias.wikipedia.models.WikiPage
import com.natacha.carthias.wikipedia.models.WikiThumbnail
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select

/**
 * Created by Natacha Carthias on 30/10/2019
 */

class HistoryRepository(val databaseHelper: ArticleDatabaseOpenHelper) {

    private val TABLE_NAME: String = "History"

    // Adds page to the database column using current data
    fun addFavorite(page: WikiPage) {
        databaseHelper.use {
            insert(TABLE_NAME,
                "id" to page.pageid,
                "title" to page.title,
                "url" to page.fullurl,
                "thumbnailJson" to Gson().toJson(page.thumbnail))
        }
    }

    // Removes page from database column
    fun removePageById(pageId: Int) {
        databaseHelper.use {
            delete(TABLE_NAME, "id = {pageId}", "pageId" to pageId)
        }
    }


    fun getAllHistory() : ArrayList<WikiPage> {
        var pages = ArrayList<WikiPage>()

        val articleRowParser = rowParser { id: Int, title: String, url: String, thumbnailJson: String ->
            val page = WikiPage()
            page.title = title
            page.pageid = id
            page.fullurl = url
            page.thumbnail = Gson().fromJson(thumbnailJson, WikiThumbnail::class.java)

            pages.add(page)
        }

        databaseHelper.use {
            select(TABLE_NAME).parseList(articleRowParser)
        }

        return pages
    }
}