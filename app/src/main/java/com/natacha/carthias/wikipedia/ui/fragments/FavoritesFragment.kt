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
import kotlinx.android.synthetic.main.fragment_favorites.*

/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : Fragment() {

    var favoritesRecycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.title = "Wikipedia"

        val view = inflater!!.inflate(R.layout.fragment_favorites, container, false)

        favoritesRecycler = view.findViewById(R.id.favorites_article_recycler)

        favoritesRecycler!!.layoutManager = LinearLayoutManager(context)
        favoritesRecycler!!.adapter = ArticleCardRecyclerAdapter()

        return view
    }


}
