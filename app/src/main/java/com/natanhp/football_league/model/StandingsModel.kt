package com.natanhp.football_league.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StandingsModel(
    @SerializedName("teamid")
    val teamId: Int,

    @SerializedName("name")
    val teamName: String,

    @Expose
    val played: Int,

    @SerializedName("goalsfor")
    val goalsFor: Int,

    @SerializedName("goalsagainst")
    val goalsAgainst: Int,

    @SerializedName("goalsdifference")
    val goalsDifference: Int,

    @Expose
    val win: Int,

    @Expose
    val draw: Int,

    @Expose
    val loss: Int,

    @Expose
    val total: Int
)