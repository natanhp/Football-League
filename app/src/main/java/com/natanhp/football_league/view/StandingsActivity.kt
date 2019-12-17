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
import com.natanhp.football_league.adapter.StandingsAdapter
import com.natanhp.football_league.model.StandingsModel
import com.natanhp.football_league.viewmodel.StandingsViewModel

class StandingsActivity : AppCompatActivity() {

    private val standingsViewModel by lazy {
        ViewModelProviders.of(this).get(StandingsViewModel::class.java)
    }

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standings)
        title = getString(R.string.standings)

        progressBar = findViewById(R.id.progress_bar)
        val standingsAdapter = StandingsAdapter()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = standingsAdapter

        progressBar.visibility = View.VISIBLE
        standingsViewModel.getStandings(intent.getIntExtra("idLeague", 0)).observe(this, Observer {
            it.let {
                val standingsTmp = ArrayList<StandingsModel>()
                standingsTmp.add(
                    StandingsModel(
                        0,
                        "Team",
                        "P",
                        "GF",
                        "GA",
                        "GD",
                        "W",
                        "D",
                        "L",
                        "Tot"
                    )
                )
                standingsTmp.addAll(it.standings)
                standingsAdapter.setStandings(standingsTmp)
                progressBar.visibility = View.GONE
            }
        })
    }


}
