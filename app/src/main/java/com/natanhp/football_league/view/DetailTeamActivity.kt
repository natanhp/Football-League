package com.natanhp.football_league.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.natanhp.football_league.R
import com.natanhp.football_league.model.TeamModel

class DetailTeamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        setViews(intent.getParcelableExtra("team"))
    }

    private fun setViews(team: TeamModel?) {
        val teamImage: ImageView = findViewById(R.id.imageViewTeam)
        val teamName: TextView = findViewById(R.id.textViewTeamName)
        val alternateName: TextView = findViewById(R.id.textViewAlternateName)
        val formedYear: TextView = findViewById(R.id.textViewFormedYear)
        val stadium: TextView = findViewById(R.id.textViewStadium)
        val stadiumDescription: TextView = findViewById(R.id.textViewStadiumDescription)
        val stadiumLocation: TextView = findViewById(R.id.textViewStadiumLocation)
        val teamDescription: TextView = findViewById(R.id.textViewTeamDescription)

        Glide.with(this)
            .load(team?.teamLogo)
            .into(teamImage)

        teamName.text = team?.teamName ?: ""
        alternateName.text = team?.alternateName ?: ""
        formedYear.text = team?.formedYear.toString()
        stadium.text = team?.stadium ?: ""
        stadiumDescription.text = team?.stadiumDescription ?: ""
        stadiumLocation.text = team?.stadiumLocation ?: ""
        teamDescription.text = team?.teamDescription ?: ""
    }
}
