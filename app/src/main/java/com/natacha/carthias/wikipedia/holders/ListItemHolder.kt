package com.natacha.carthias.wikipedia.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.natacha.carthias.wikipedia.R
import kotlinx.android.synthetic.main.article_card_item.view.*

/**
 * Created by Natacha Carthias on 28/10/2019
 */

class ListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val articleImageView: ImageView = itemView.findViewById(R.id.result_icon)
    private val titleTextView: TextView = itemView.findViewById(R.id.result_title)

}