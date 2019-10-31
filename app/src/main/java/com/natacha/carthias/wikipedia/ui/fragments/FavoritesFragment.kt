package com.natacha.carthias.wikipedia.ui.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.natacha.carthias.wikipedia.R
import com.natacha.carthias.wikipedia.WikiApplication
import com.natacha.carthias.wikipedia.adapters.ArticleCardRecyclerAdapter
import com.natacha.carthias.wikipedia.managers.WikiManager
import com.natacha.carthias.wikipedia.models.WikiPage
import org.jetbrains.anko.doAsync

/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : Fragment() {

    private val adapter = ArticleCardRecyclerAdapter()
    private var wikiManager: WikiManager? = null
    var favoritesRecycler: RecyclerView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        wikiManager = (activity?.applicationContext as WikiApplication).wikiManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.title = "Wikipedia"

        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        favoritesRecycler = view.findViewById(R.id.favorites_article_recycler)

        favoritesRecycler!!.layoutManager = StaggeredGridLayoutManager(2,
            StaggeredGridLayoutManager.VERTICAL)
        favoritesRecycler!!.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()

        // Does not run on the UI thread
        doAsync {
            val favoriteArticles = wikiManager!!.getFavorites()
            adapter.currentResults.clear()
            adapter.currentResults.addAll(favoriteArticles as ArrayList<WikiPage>)

            // Adapter must be run on UI thread
            activity?.runOnUiThread{adapter.notifyDataSetChanged()}
        }
    }
}
