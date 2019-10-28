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

import com.natacha.carthias.wikipedia.R
import com.natacha.carthias.wikipedia.activities.SearchActivity
import com.natacha.carthias.wikipedia.adapters.ArticleCardRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_explore.*

/**
 * A simple [Fragment] subclass.
 */
class ExploreFragment : Fragment() {

    var searchCardView: CardView? = null
    var exploreRecycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater!!.inflate(R.layout.fragment_explore, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Wikipedia"

        searchCardView = view.findViewById(R.id.search_card_view)
        exploreRecycler = view.findViewById(R.id.explore_article_recycler)

        searchCardView!!.setOnClickListener{
            val searchIntent = Intent(context, SearchActivity::class.java)
            context!!.startActivity(searchIntent)
        }

        exploreRecycler!!.layoutManager = LinearLayoutManager(context)
        exploreRecycler!!.adapter = ArticleCardRecyclerAdapter()

        return view
    }


}
