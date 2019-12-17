package com.natanhp.football_league.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.natanhp.football_league.R
import com.natanhp.football_league.model.StandingsModel

class StandingsAdapter : RecyclerView.Adapter<StandingsAdapter.ViewHolder>() {
    private var standings = ArrayList<StandingsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.standing_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = standings.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binder(standings[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamName: TextView = itemView.findViewById(R.id.textViewTeamName)
        private val played: TextView = itemView.findViewById(R.id.textViewPlayed)
        private val goalsFor: TextView = itemView.findViewById(R.id.textViewGoalsFor)
        private val goalsAgainst: TextView = itemView.findViewById(R.id.textViewGoalsAgainst)
        private val goalsDifference: TextView = itemView.findViewById(R.id.textViewGoalsDifference)
        private val win: TextView = itemView.findViewById(R.id.textViewWin)
        private val draw: TextView = itemView.findViewById(R.id.textViewDraw)
        private val loss: TextView = itemView.findViewById(R.id.textViewLoss)
        private val total: TextView = itemView.findViewById(R.id.textViewTotal)

        fun binder(standings: StandingsModel) {
            teamName.text = standings.teamName
            played.text = standings.played
            goalsFor.text = standings.goalsFor
            goalsAgainst.text = standings.goalsAgainst
            goalsDifference.text = standings.goalsDifference
            win.text = standings.win
            draw.text = standings.draw
            loss.text = standings.loss
            total.text = standings.total
        }
    }

    fun setStandings(standings: ArrayList<StandingsModel>) {
        this.standings.clear()
        this.standings.addAll(standings)
        notifyDataSetChanged()
    }

}