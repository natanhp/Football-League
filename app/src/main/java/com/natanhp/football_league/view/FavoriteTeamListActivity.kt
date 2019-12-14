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
import com.natanhp.football_league.model.TeamModel
import com.natanhp.football_league.viewmodel.TeamViewModel
import org.jetbrains.anko.startActivity

class FavoriteTeamListActivity : AppCompatActivity() {

    private val teamViewModel by lazy {
        ViewModelProviders.of(this).get(TeamViewModel::class.java)
    }

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_team_list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val teamAdapter = TeamAdapter {
            startActivity<DetailTeamActivity>("team" to it)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = teamAdapter

        progressBar = findViewById(R.id.progressBar)

        progressBar.visibility = View.VISIBLE
        teamViewModel.showFavoriteTeams(this).observe(this, Observer {
            it.let {
                val teamTmp = ArrayList<TeamModel>()
                teamTmp.addAll(it)
                teamAdapter.setTeams(teamTmp)
                progressBar.visibility = View.GONE
            }
        })
    }
}

