package com.natanhp.football_league.model

import com.natanhp.football_league.modeldata.MatchModel
import com.google.gson.annotations.SerializedName


data class MatchModels(

    @SerializedName("events")
    private val matches: ArrayList<MatchModel>
){
    fun getMatches(): ArrayList<MatchModel> {
        return matches
    }
}