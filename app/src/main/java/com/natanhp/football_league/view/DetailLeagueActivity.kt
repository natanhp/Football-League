package com.natanhp.football_league.view

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.natanhp.football_league.R
import com.natanhp.football_league.model.LeagueModel
import org.jetbrains.anko.*

class DetailLeagueActivity : AppCompatActivity() {

    private var leagueModel: LeagueModel? = LeagueModel(0, "", 0, "", "")
    private var idLeague: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailLeagueUI().setContentView(this)

        leagueModel = intent.getParcelableExtra("league")

        val imageLogo: ImageView = findViewById(R.id.img_logo)
        val textName: TextView = findViewById(R.id.tv_name)
        val textDesc: TextView = findViewById(R.id.tv_desc)

        leagueModel.let {
            idLeague = it?.getId() ?: 0
            Glide.with(this)
                .load(leagueModel?.getOnlineLogo())
                .into(imageLogo)

            textName.text = leagueModel?.getName()
            textDesc.text = leagueModel?.getDesc()
        }
    }

    private class DetailLeagueUI : AnkoComponent<DetailLeagueActivity> {

        override fun createView(ui: AnkoContext<DetailLeagueActivity>): View {
            return ui.scrollView {
                verticalLayout {
                    imageView {
                        id = R.id.img_logo
                    }.lparams {
                        width = 250
                        height = 250
                        gravity = Gravity.CENTER_HORIZONTAL
                        topMargin = 16
                    }

                    textView {
                        id = R.id.tv_name
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams {
                        topMargin = 8
                        gravity = Gravity.CENTER_HORIZONTAL
                    }

                    textView {
                        id = R.id.tv_desc
                    }.lparams {
                        margin = 8
                    }
                }

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.detail_league_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.matches) {
            startActivity<MatchActivity>("match" to leagueModel)
        } else if (item.itemId == R.id.standings) {
            startActivity<StandingsActivity>("idLeague" to idLeague)
        } else if (item.itemId == R.id.teams) {
            startActivity<TeamListActivity>("idLeague" to idLeague)
        }

        return true
    }
}

