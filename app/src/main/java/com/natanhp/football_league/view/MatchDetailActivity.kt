package com.natanhp.football_league.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.natanhp.football_league.R
import com.natanhp.football_league.modeldata.MatchModel

class MatchDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        progressBar.visibility = View.VISIBLE


        val match: MatchModel = intent.getParcelableExtra("match_detail")
        match?.let {
            title = "${it.teamHome} vs ${it.teamAway}"
            initViews(it)
            progressBar.visibility = View.GONE
        }
    }

    private fun initViews(match: MatchModel){
        val homeTeamName: TextView = findViewById(R.id.text_home_team)
        val homeTeamScore: TextView = findViewById(R.id.text_home_score)
        val homeTeamGoalDetail: TextView = findViewById(R.id.text_home_goal_detail)
        val homeTeamRedCard: TextView = findViewById(R.id.text_home_red_card)
        val homeTeamYellowCard: TextView = findViewById(R.id.text_home_yellow_card)
        val homeTeamLineUpGoalKeeper: TextView = findViewById(R.id.text_home_line_up_goal_keeper)

        val awayTeamName: TextView = findViewById(R.id.text_away_team)
        val awayTeamScore: TextView = findViewById(R.id.text_away_score)
        val awayTeamGoalDetail: TextView = findViewById(R.id.text_away_goal_detail)
        val awayTeamRedCard: TextView = findViewById(R.id.text_away_red_card)
        val awayTeamYellowCard: TextView = findViewById(R.id.text_away_yellow_card)
        val awayTeamLineUpGoalKeeper: TextView = findViewById(R.id.text_away_line_up_goal_keeper)

        homeTeamName.text = match.teamHome
        homeTeamScore.text = match.homeScore.toString()
        homeTeamGoalDetail.text = match.homeGoalDetail
        homeTeamRedCard.text = match.homeRedCard
        homeTeamYellowCard.text = match.homeYellowCard
        homeTeamLineUpGoalKeeper.text = match.homeLineUpGoalKeeper

        awayTeamName.text = match.teamAway
        awayTeamScore.text = match.awayScore.toString()
        awayTeamGoalDetail.text = match.awayGoalDetail
        awayTeamRedCard.text = match.awayRedCard
        awayTeamYellowCard.text = match.awayYellowCard
        awayTeamLineUpGoalKeeper.text = match.awayLineUpGoalKeeper
    }
}
