package com.natanhp.football_league.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.natanhp.football_league.R
import com.natanhp.football_league.adapter.MatchAdapter
import com.natanhp.football_league.viewmodel.MatchViewModel
import org.jetbrains.anko.sdk27.coroutines.onQueryTextListener
import org.jetbrains.anko.toast

class SearchMatchActivity : AppCompatActivity() {
    private val matchViewModel: MatchViewModel by lazy {
        ViewModelProviders.of(this).get(MatchViewModel::class.java)
    }

    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        progressBar = findViewById(R.id.progress_bar)

        val searchView: SearchView = findViewById(R.id.search_bar)
        val recyclerView: RecyclerView = findViewById(R.id.match_recyclerview)
        val matchAdapter = MatchAdapter {
            toast("Hapie")
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = matchAdapter

        searchView.isIconifiedByDefault = false
        searchView.onQueryTextListener {
            this.onQueryTextSubmit() {
                progressBar.visibility = View.VISIBLE

                matchViewModel.searchMatches(searchView.query)
                    .observe(this@SearchMatchActivity, Observer {
                        it?.let {
                            matchAdapter.setMatch(it.matches)
                            progressBar.visibility = View.GONE
                        }
                    })

                return@onQueryTextSubmit true
            }
        }
    }
}
