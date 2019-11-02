package com.natanhp.football_league.view

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import com.bumptech.glide.Glide
import com.natanhp.football_league.R
import com.natanhp.football_league.model.LeagueModel
import org.jetbrains.anko.*

class DetailLeagueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailLeagueUI().setContentView(this)

        val leagueModel = intent.getParcelableExtra<LeagueModel>("league")

        val imageLogo = findViewById(R.id.img_logo) as ImageView
        val textName = findViewById(R.id.tv_name) as TextView
        val textDesc = findViewById(R.id.tv_desc) as TextView

        Glide.with(this)
            .load(getDrawable(leagueModel.getLogo()))
            .into(imageLogo)

        textName.text = leagueModel.getName()
        textDesc.text = leagueModel.getDesc()
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
                    }.lparams{
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

}

