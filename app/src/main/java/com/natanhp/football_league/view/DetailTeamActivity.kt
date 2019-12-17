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
import com.natanhp.football_league.viewmodel.TeamViewModel

class DetailTeamActivity : AppCompatActivity() {

    private val teamViewModel by lazy {
        ViewModelProviders.of(this).get(TeamViewModel::class.java)
    }

    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null
    private lateinit var progressBar: ProgressBar
    private val team: TeamModel by lazy {
        intent.getParcelableExtra<TeamModel>("team")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        setViews(team)

        progressBar = findViewById(R.id.progress_bar)

        team.let {
            title = it.teamName
        }

        getFavoriteState()
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

    private fun getFavoriteState() {
        progressBar.visibility = View.VISIBLE
        team.let {
            teamViewModel.getFavoriteStateTeam(this, it.idTeam.toLong()).observe(this, Observer {
                isFavorite = !it.isNullOrEmpty()
            })
            progressBar.visibility = View.GONE
        }
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
        team.let { teamViewModel.addToFavoriteTeam(this, it) }
    }

    private fun removeFromFavorite() {
        team.let {
            teamViewModel.removeFromFavoriteTeam(this, it.idTeam.toLong())
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
}
