package com.natanhp.football_league.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.natanhp.football_league.R
import com.natanhp.football_league.modeldata.MatchModel
import kotlinx.android.extensions.LayoutContainer

class MatchAdapter(private val listener: (MatchModel) -> Unit) :
    RecyclerView.Adapter<MatchAdapter.ViewHolder>() {
    private var matches = ArrayList<MatchModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false))

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewBinder(matches[position], listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        private val homeTeamName: TextView = itemView.findViewById(R.id.text_home_team)
        private val homeTeamScore: TextView = itemView.findViewById(R.id.text_home_score)
        private val awayTeamName: TextView = itemView.findViewById(R.id.text_away_team)
        private val awayTeamScore: TextView = itemView.findViewById(R.id.text_away_score)

        fun viewBinder(match: MatchModel, listener: (MatchModel) -> Unit) {
            homeTeamName.text = match.teamHome
            homeTeamScore.text = match.homeScore.toString()
            awayTeamName.text = match.teamAway
            awayTeamScore.text = match.awayScore.toString()

            itemView.setOnClickListener {
                listener(match)
            }
        }
    }

    fun setMatch(matches: ArrayList<MatchModel>) {
        this.matches = matches
    }

}