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
import com.natanhp.football_league.adapter.TeamAdapter
import com.natanhp.football_league.viewmodel.TeamViewModel

class TeamListActivity : AppCompatActivity() {

    private val teamViewModel by lazy {
        ViewModelProviders.of(this).get(TeamViewModel::class.java)
    }

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val teamAdapter = TeamAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = teamAdapter

        progressBar = findViewById(R.id.progressBar)

        progressBar.visibility = View.VISIBLE
        teamViewModel.getTeamList(intent.getIntExtra("idLeague", 0)).observe(this, Observer {
            it.teams.let {
                teamAdapter.setTeams(it)
                progressBar.visibility = View.GONE
            }
        })
    }
}
