package com.natacha.carthias.wikipedia.ui.fragments


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem

import com.natacha.carthias.wikipedia.R
import com.natacha.carthias.wikipedia.WikiApplication
import com.natacha.carthias.wikipedia.adapters.ArticleListItemRecyclerAdapter
import com.natacha.carthias.wikipedia.managers.WikiManager
import com.natacha.carthias.wikipedia.models.WikiPage
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton


/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : Fragment() {

    private val adapter = ArticleListItemRecyclerAdapter()
    private var wikiManager: WikiManager? = null
    var historyRecycler: RecyclerView? = null

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


        // Alerts fragment to options menu
        setHasOptionsMenu(true)

        val view = inflater.inflate(R.layout.fragment_history, container, false)

        historyRecycler = view.findViewById(R.id.history_article_recycler)

        historyRecycler!!.layoutManager = LinearLayoutManager(context)
        historyRecycler!!.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()

        // Does not run on the UI thread
        doAsync {
            val history = wikiManager!!.getHistory()
            adapter.currentResults.clear()
            adapter.currentResults.addAll(history as ArrayList<WikiPage>)

            // Adapter must be run on UI thread
            activity?.runOnUiThread{adapter.notifyDataSetChanged()}
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.history_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item!!.itemId == R.id.action_clear_history) {
            activity?.alert("Are you sure you want to clear your history?", "Confirm") {
                yesButton {
                    // Clear history using Async
                    adapter.currentResults.clear()
                    doAsync {
                        wikiManager?.clearHistory()
                    }
                    // Call adapter on UI thread
                    activity!!.runOnUiThread{ adapter.notifyDataSetChanged()}
                }
                noButton {  }
            }?.show()
        }

        return true
    }
}
