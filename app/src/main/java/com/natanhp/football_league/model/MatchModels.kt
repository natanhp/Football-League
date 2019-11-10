package com.natanhp.football_league.model

import com.google.gson.annotations.SerializedName
import com.natanhp.football_league.modeldata.MatchModel


data class MatchModels(

    @SerializedName("events")
    private val matches: ArrayList<MatchModel>
) {
    fun getMatches(): ArrayList<MatchModel>? {
        return matches
    }
}