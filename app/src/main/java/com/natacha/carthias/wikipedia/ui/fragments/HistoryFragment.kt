package com.natacha.carthias.wikipedia.ui.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.natacha.carthias.wikipedia.R
import com.natacha.carthias.wikipedia.activities.SearchActivity
import com.natacha.carthias.wikipedia.adapters.ArticleCardRecyclerAdapter
import com.natacha.carthias.wikipedia.adapters.ArticleListItemRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlinx.android.synthetic.main.fragment_history.*

/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : Fragment() {

    var historyRecycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.title = "Wikipedia"

        val view = inflater!!.inflate(R.layout.fragment_history, container, false)

        historyRecycler = view.findViewById(R.id.history_article_recycler)

        historyRecycler!!.layoutManager = LinearLayoutManager(context)
        historyRecycler!!.adapter = ArticleListItemRecyclerAdapter()

        return view
    }


}
