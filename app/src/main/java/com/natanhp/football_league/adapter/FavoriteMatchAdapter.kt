package com.natanhp.football_league.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.natanhp.football_league.R
import com.natanhp.football_league.model.FavoriteMatchModel
import kotlinx.android.extensions.LayoutContainer

class FavoriteMatchAdapter(private val listener: (FavoriteMatchModel) -> Unit) :
    RecyclerView.Adapter<FavoriteMatchAdapter.ViewHolder>() {
    private var matches = ArrayList<FavoriteMatchModel>()

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

        fun viewBinder(match: FavoriteMatchModel, listener: (FavoriteMatchModel) -> Unit) {
            homeTeamName.text = match.homeTeamName
            if (match.homeTeamScore == null) {
                homeTeamScore.text = "-"
            } else {
                homeTeamScore.text = match.homeTeamScore
            }

            awayTeamName.text = match.awayTeamName
            if (match.awayTeamScore == null) {
                awayTeamScore.text = "-"
            } else {
                awayTeamScore.text = match.awayTeamScore
            }

            itemView.setOnClickListener {
                listener(match)
            }
        }
    }

    fun setMatch(matches: List<FavoriteMatchModel>) {
        this.matches.addAll(matches)
        notifyDataSetChanged()
    }

}