package com.natanhp.football_league.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.natanhp.football_league.R
import com.natanhp.football_league.model.LeagueModel
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.*

class LeagueAdapter(private val listener: (LeagueModel) -> Unit) :
    RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    private var leagues = ArrayList<LeagueModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LeageItemUI().createView(AnkoContext.Companion.create(parent.context)))

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewBinder(leagues[position], listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        private val leageLogo: ImageView = itemView.findViewById(R.id.img_logo)
        private val textViewName: TextView = itemView.findViewById(R.id.tv_name)

        fun viewBinder(league: LeagueModel, listener: (LeagueModel) -> Unit) {
            Glide.with(itemView.context)
                .load(itemView.context.getDrawable(league.getLogo()))
                .into(leageLogo)

            textViewName.text = league.getName()

            itemView.setOnClickListener {
                listener(league)
            }
        }
    }

    private class LeageItemUI : AnkoComponent<Context> {
        override fun createView(ui: AnkoContext<Context>): View {
            return ui.linearLayout {

                lparams { margin = 16 }

                imageView {
                    id = R.id.img_logo
                }.lparams(
                    width = 250,
                    height = 250
                )
                textView {
                    id = R.id.tv_name
                }.lparams {
                    margin = 8
                }
            }
        }

    }

    fun setLeagues(leagues: ArrayList<LeagueModel>) {
        this.leagues = leagues
        notifyDataSetChanged()
    }

}