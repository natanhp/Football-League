package com.natanhp.football_league.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.natanhp.football_league.R
import com.natanhp.football_league.model.TeamModel

class TeamAdapter(private val listener: (TeamModel) -> Unit) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    private var teams = ArrayList<TeamModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.team_item, parent, false
        )
    )

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binder(teams[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamImage: ImageView = itemView.findViewById(R.id.imageViewTeam)
        private val teamName: TextView = itemView.findViewById(R.id.textViewTeamName)

        fun binder(team: TeamModel, listener: (TeamModel) -> Unit) {
            Glide.with(itemView.context)
                .load(team.teamLogo)
                .into(teamImage)

            teamName.text = team.teamName

            itemView.setOnClickListener {
                listener(team)
            }
        }

    }

    fun setTeams(teams: ArrayList<TeamModel>) {
        this.teams.clear()
        this.teams.addAll(teams)
        notifyDataSetChanged()
    }

}