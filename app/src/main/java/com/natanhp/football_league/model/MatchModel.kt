package com.natanhp.football_league.modeldata

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchModel(

    @SerializedName("idEvent")
    val idMatch: Int = 0,

    @SerializedName("strHomeTeam")
    val teamHome: String = "",

    @SerializedName("strAwayTeam")
    val teamAway: String = "",

    @SerializedName("intHomeScore")
    val homeScore: Int = 0,

    @SerializedName("intAwayScore")
    val awayScore: Int = 0,

    @SerializedName("strHomeGoalDetails")
    val homeGoalDetail: String = "",

    @SerializedName("strHomeRedCards")
    val homeRedCard: String = "",

    @SerializedName("strHomeYellowCards")
    val homeYellowCard: String = "",

    @SerializedName("strHomeLineupGoalkeeper")
    val homeLineUpGoalKeeper: String = "",

    @SerializedName("strAwayGoalDetails")
    val awayGoalDetail: String = "",

    @SerializedName("strAwayRedCards")
    val awayRedCard: String = "",

    @SerializedName("strAwayYellowCards")
    val awayYellowCard: String = "",

    @SerializedName("strAwayLineupGoalkeeper")
    val awayLineUpGoalKeeper: String = ""
) : Parcelable