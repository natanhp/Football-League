package com.natanhp.football_league.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamModel(

    @SerializedName("idTeam")
    val idTeam: Int,

    @SerializedName("strTeamBadge")
    val teamLogo: String,

    @SerializedName("strTeam")
    val teamName: String,

    @SerializedName("strAlternate")
    val alternateName: String,

    @SerializedName("intFormedYear")
    val formedYear: Int,

    @SerializedName("strStadium")
    val stadium: String,

    @SerializedName("strStadiumDescription")
    val stadiumDescription: String,

    @SerializedName("strStadiumLocation")
    val stadiumLocation: String,

    @SerializedName("strDescriptionEN")
    val teamDescription: String
) : Parcelable