package com.natanhp.football_league.model

import com.google.gson.annotations.SerializedName

data class LeaguesModel(
    @SerializedName("leagues")
    private val leagueModels: ArrayList<LeagueModel>
) {
    fun getLeageuModels() = leagueModels
}