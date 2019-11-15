package com.natanhp.football_league.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.natanhp.football_league.R
import com.natanhp.football_league.adapter.FavoriteMatchAdapter
import com.natanhp.football_league.viewmodel.MatchViewModel
import org.jetbrains.anko.startActivity

class FavoriteMatchActivity : AppCompatActivity() {

    private lateinit var matchViewModel: MatchViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: FavoriteMatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_match)

        progressBar = findViewById(R.id.progress_bar)

        val recyclerview: RecyclerView = findViewById(R.id.match_recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)

        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)

        adapter = FavoriteMatchAdapter {
            progressBar.visibility = View.VISIBLE

            it.let {
                matchViewModel.getMatchDetail(it.matchId?.toInt()).observe(this, Observer {
                    it.let {
                        progressBar.visibility = View.GONE
                        startActivity<MatchDetailActivity>("match_detail" to it.getMatches()?.get(0))
                    }
                })
            }
        }

        recyclerview.adapter = adapter
        getData()

    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData() {
        progressBar.visibility = View.VISIBLE
        matchViewModel.showFavoriteMatches(this).observe(this, Observer {
            it.let {
                adapter.setMatch(it)
                progressBar.visibility = View.GONE
            }
        })
    }
}
