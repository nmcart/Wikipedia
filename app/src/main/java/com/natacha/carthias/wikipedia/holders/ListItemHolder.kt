package com.natacha.carthias.wikipedia.holders

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.natacha.carthias.wikipedia.R
import com.natacha.carthias.wikipedia.activities.ArticleDetailActivity
import com.natacha.carthias.wikipedia.models.WikiPage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_card_item.view.*

/**
 * Created by Natacha Carthias on 28/10/2019
 */

class ListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val articleImageView: ImageView = itemView.findViewById(R.id.result_icon)
    private val titleTextView: TextView = itemView.findViewById(R.id.result_title)

    private var currentPage: WikiPage? = null

    init {
        itemView.setOnClickListener{ view: View? ->
            var detailPageIntent = Intent(itemView.context, ArticleDetailActivity::class.java)
            var pageJson = Gson().toJson(currentPage)
            detailPageIntent.putExtra("page", pageJson)
            itemView.context.startActivity(detailPageIntent)
        }
    }

    fun updateWithPage(page: WikiPage){


        if(page.thumbnail != null)
            Picasso.with(itemView.context).load(page.thumbnail!!.source).into(articleImageView)

        titleTextView.text = page.title
        currentPage = page
    }

}