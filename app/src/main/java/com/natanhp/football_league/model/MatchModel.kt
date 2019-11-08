package com.natanhp.football_league.modeldata

import com.google.gson.annotations.SerializedName

data class MatchModel(

    @SerializedName("idEvent")
    private val idMatch: Int,

    @SerializedName("strHomeTeam")
    private val teamHome: String,

    @SerializedName("strAwayTeam")
    private val teamAway: String,

    @SerializedName("intHomeScore")
    private val homeScore: Int,

    @SerializedName("intAwayScore")
    private val awayScore: Int,

    @SerializedName("strHomeGoalDetails")
    private val homeGoalDetail: String,

    @SerializedName("strHomeRedCards")
    private val homeRedCard: String,

    @SerializedName("strHomeYellowCards")
    private val homeYellowCard: String,

    @SerializedName("strHomeLineupGoalkeeper")
    private val homeLineUpGoalKeeper: String,

    @SerializedName("strAwayGoalDetails")
    private val awayGoalDetail: String,

    @SerializedName("strAwayRedCards")
    private val awayRedCard: String,

    @SerializedName("strAwayYellowCards")
    private val awayYellowCard: String,

    @SerializedName("strAwayLineupGoalkeeper")
    private val awayLineUpGoalKeeper: String
)