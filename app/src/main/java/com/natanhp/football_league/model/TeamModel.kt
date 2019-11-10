package com.natanhp.football_league.model

import com.google.gson.annotations.SerializedName

data class TeamModel(

    @SerializedName("idTeam")
    val idTeam: Int,

    @SerializedName("strTeamBadge")
    val teamLogo: String
)