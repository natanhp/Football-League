package com.natanhp.football_league.view

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.natanhp.football_league.adapter.LeagueAdapter
import com.natanhp.football_league.model.LeagueModel
import com.natanhp.football_league.viewmodel.LeagueViewModel
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    lateinit var linearLayout: LinearLayout

    private val leagueViewModel: LeagueViewModel by lazy {
        ViewModelProviders.of(this).get(LeagueViewModel::class.java)
    }

    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val leagueAdapter = LeagueAdapter {
            progressBar.visibility = View.VISIBLE
            val id = it.getId()
            leagueViewModel.leagueDetail(id).observe(this, Observer {
                if (id == it.getId()) {
                    startActivity<DetailLeagueActivity>("league" to it)
                }
            })
        }

        val observer = Observer<ArrayList<LeagueModel>> { leagueAdapter.setLeagues(it) }


        leagueViewModel.leagueModel.observe(this, observer)

        linearLayout = linearLayout {
            progressBar = progressBar() {
                visibility = View.GONE
            }.lparams {
                gravity = Gravity.CENTER
            }
            recyclerView {
                layoutManager = LinearLayoutManager(context)
                adapter = leagueAdapter
            }.lparams {
                width = matchParent
                height = wrapContent
            }
        }
    }

    override fun onResume() {
        super.onResume()
        progressBar.visibility = View.GONE
    }
}
