package com.natanhp.football_league.model

import com.google.gson.annotations.SerializedName
import com.natanhp.football_league.modeldata.MatchModel

data class MatchModelsSearch(
    @SerializedName("event")
    val matches: ArrayList<MatchModel>
)