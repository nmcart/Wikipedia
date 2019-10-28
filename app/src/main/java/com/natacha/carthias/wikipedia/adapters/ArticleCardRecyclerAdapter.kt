package com.natacha.carthias.wikipedia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.natacha.carthias.wikipedia.R
import com.natacha.carthias.wikipedia.holders.CardHolder

/**
 * Created by Natacha Carthias on 28/10/2019
 */

class ArticleCardRecyclerAdapter : RecyclerView.Adapter<CardHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        var cardItem = LayoutInflater.from(parent?.context).inflate(R.layout.article_card_item, parent, false)
        return CardHolder(cardItem)
    }

    override fun getItemCount(): Int {
        return 15
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {

    }
}