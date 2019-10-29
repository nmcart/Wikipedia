package com.natacha.carthias.wikipedia.ui.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.natacha.carthias.wikipedia.R
import com.natacha.carthias.wikipedia.activities.SearchActivity
import com.natacha.carthias.wikipedia.adapters.ArticleCardRecyclerAdapter
import com.natacha.carthias.wikipedia.provider.ArticleDataProvider


/**
 * A simple [Fragment] subclass.
 */
class ExploreFragment : Fragment() {

    private val articleProvider: ArticleDataProvider = ArticleDataProvider()
    var searchCardView: CardView? = null
    var exploreRecycler: RecyclerView? = null
    var refresher: SwipeRefreshLayout? = null
    var adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater!!.inflate(R.layout.fragment_explore, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Wikipedia"

        searchCardView = view.findViewById(R.id.search_card_view)
        exploreRecycler = view.findViewById(R.id.explore_article_recycler)
        refresher = view.findViewById(R.id.refresher)

        searchCardView!!.setOnClickListener{
            val searchIntent = Intent(context, SearchActivity::class.java)
            context!!.startActivity(searchIntent)
        }

        exploreRecycler!!.layoutManager = LinearLayoutManager(context)
        exploreRecycler!!.adapter = adapter

        refresher?.setOnRefreshListener {
            refresher?.isRefreshing = true
            getRandomArticles()
        }

        getRandomArticles()
        return view
    }

    private fun getRandomArticles() {
        refresher?.isRefreshing = true


        articleProvider.getRandom(15, {wikiResult ->
            adapter.currentResults.clear()
            adapter.currentResults.addAll(wikiResult.query!!.pages)
            activity!!.runOnUiThread {adapter.notifyDataSetChanged()
                refresher?.isRefreshing = false
            }
        })


    }

}
