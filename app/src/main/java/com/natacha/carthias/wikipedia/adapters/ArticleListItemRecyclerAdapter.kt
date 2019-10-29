package com.natacha.carthias.wikipedia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.natacha.carthias.wikipedia.R
import com.natacha.carthias.wikipedia.holders.CardHolder
import com.natacha.carthias.wikipedia.holders.ListItemHolder
import com.natacha.carthias.wikipedia.models.WikiPage

/**
 * Created by Natacha Carthias on 28/10/2019
 */

class ArticleListItemRecyclerAdapter : RecyclerView.Adapter<ListItemHolder>() {
    val currentResults : ArrayList<WikiPage> = ArrayList<WikiPage>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ListItemHolder {
        var listItem = LayoutInflater.from(parent?.context).inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(listItem)
    }

    override fun getItemCount(): Int {
        return currentResults.size
}

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {

        var page = currentResults[position]
        holder?.updateWithPage(page)

    }
}