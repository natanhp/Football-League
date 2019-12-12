package com.natanhp.football_league.model

import com.google.gson.annotations.SerializedName

data class StandingsModels(

    @SerializedName("table")
    val standings: ArrayList<StandingsModel>
)