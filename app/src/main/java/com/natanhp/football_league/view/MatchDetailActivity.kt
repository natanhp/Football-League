package com.natanhp.football_league.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.natanhp.football_league.R
import com.natanhp.football_league.model.TeamModel
import com.natanhp.football_league.modeldata.MatchModel
import com.natanhp.football_league.viewmodel.TeamViewModel

class MatchDetailActivity : AppCompatActivity() {

    private lateinit var teamViewModel: TeamViewModel
    private val teamModel = ArrayList<TeamModel>()
    private lateinit var progressBar: ProgressBar
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var match: MatchModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        teamViewModel = ViewModelProviders.of(this).get(TeamViewModel::class.java)

        progressBar = findViewById(R.id.progress_bar)

        progressBar.visibility = View.VISIBLE

        match = intent.getParcelableExtra("match_detail")


        match?.let {
            title = "${it.teamHome} vs ${it.teamAway}"
            val matchModel = it
            teamViewModel.getDetailTeam(matchModel.homeTeamId).observe(this, Observer {
                val teamModelTmp = it
                teamModelTmp.let {
                    teamModel.add(teamModelTmp[0])
                    teamViewModel.getDetailTeam(matchModel.awayTeamId).observe(this, Observer {
                        if (teamModelTmp[0].idTeam != it[0].idTeam) {
                            teamModel.add(it[0])
                            initViews()
                        }


                    })
                }
            })
        }

        getFavoriteState()
    }

    private fun initViews() {
        val homeTeamName: TextView = findViewById(R.id.text_home_team)
        val homeTeamScore: TextView = findViewById(R.id.text_home_score)
        val homeTeamGoalDetail: TextView = findViewById(R.id.text_home_goal_detail)
        val homeTeamRedCard: TextView = findViewById(R.id.text_home_red_card)
        val homeTeamYellowCard: TextView = findViewById(R.id.text_home_yellow_card)
        val homeTeamLineUpGoalKeeper: TextView = findViewById(R.id.text_home_line_up_goal_keeper)
        val homeTeamLogo: ImageView = findViewById(R.id.logo_home)

        val awayTeamName: TextView = findViewById(R.id.text_away_team)
        val awayTeamScore: TextView = findViewById(R.id.text_away_score)
        val awayTeamGoalDetail: TextView = findViewById(R.id.text_away_goal_detail)
        val awayTeamRedCard: TextView = findViewById(R.id.text_away_red_card)
        val awayTeamYellowCard: TextView = findViewById(R.id.text_away_yellow_card)
        val awayTeamLineUpGoalKeeper: TextView = findViewById(R.id.text_away_line_up_goal_keeper)
        val awayTeamLogo: ImageView = findViewById(R.id.logo_away)

        homeTeamName.text = match?.teamHome ?: ""

        if (match?.homeScore == null) {
            homeTeamScore.text = "-"
        } else {
            homeTeamScore.text = match?.homeScore.toString()
        }

        homeTeamGoalDetail.text = match?.homeGoalDetail ?: ""
        homeTeamRedCard.text = match?.homeRedCard ?: ""
        homeTeamYellowCard.text = match?.homeYellowCard ?: ""
        homeTeamLineUpGoalKeeper.text = match?.homeLineUpGoalKeeper ?: ""

        Glide.with(this)
            .load(teamModel[0].teamLogo)
            .into(homeTeamLogo)


        awayTeamName.text = match?.teamAway ?: ""
        if (match?.awayScore == null) {
            awayTeamScore.text = "-"
        } else {
            awayTeamScore.text = match?.awayScore.toString()
        }

        awayTeamGoalDetail.text = match?.awayGoalDetail ?: ""
        awayTeamRedCard.text = match?.awayRedCard ?: ""
        awayTeamYellowCard.text = match?.awayYellowCard ?: ""
        awayTeamLineUpGoalKeeper.text = match?.awayLineUpGoalKeeper ?: ""

        Glide.with(this)
            .load(teamModel[1].teamLogo)
            .into(awayTeamLogo)

        progressBar.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        menuItem = menu
        setFavorite()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()

                true
            }

            R.id.add_to_favorite -> {
                if (isFavorite) {
                    removeFromFavorite()
                } else {
                    addToFavorite()
                }

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        match?.let { teamViewModel.addToFavorite(this, it) }
    }

    private fun removeFromFavorite() {
        match?.let {
            teamViewModel.removeFromFavorite(this, it.idMatch.toLong())
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        } else {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }

    private fun getFavoriteState() {
        progressBar.visibility = View.VISIBLE
        match?.let {
            teamViewModel.getFavoriteState(this, it.idMatch.toLong()).observe(this, Observer {
                isFavorite = !it.isNullOrEmpty()
            })
        }
    }
}
