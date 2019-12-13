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
import com.natanhp.football_league.adapter.TeamAdapter
import com.natanhp.football_league.model.TeamModel
import com.natanhp.football_league.viewmodel.TeamViewModel
import org.jetbrains.anko.sdk27.coroutines.onQueryTextListener
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SearchTeamActivity : AppCompatActivity() {

    private val teamViewModel by lazy {
        ViewModelProviders.of(this).get(TeamViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        val teamAdapter = TeamAdapter {
            startActivity<DetailTeamActivity>("team" to it)
        }
        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = teamAdapter

        val searchView: SearchView = findViewById(R.id.search_bar)

        searchView.isIconifiedByDefault = false
        searchView.onQueryTextListener {
            this.onQueryTextSubmit {
                progressBar.visibility = View.VISIBLE

                teamViewModel.searchTeam(searchView.query)
                    .observe(this@SearchTeamActivity, Observer {

                        if (it.isNullOrEmpty()) {
                            toast("Pencarian tidak ditemukan")
                            progressBar.visibility = View.GONE

                            return@Observer
                        }

                        it.let {
                            teamAdapter.setTeams(it as ArrayList<TeamModel>)
                            progressBar.visibility = View.GONE
                        }
                    })

                return@onQueryTextSubmit true
            }
        }
    }
}
