package com.natanhp.football_league.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StandingsModel(
    @SerializedName("teamid")
    val teamId: Int,

    @SerializedName("name")
    val teamName: String,

    @Expose
    val played: String,

    @SerializedName("goalsfor")
    val goalsFor: String,

    @SerializedName("goalsagainst")
    val goalsAgainst: String,

    @SerializedName("goalsdifference")
    val goalsDifference: String,

    @Expose
    val win: String,

    @Expose
    val draw: String,

    @Expose
    val loss: String,

    @Expose
    val total: String
)