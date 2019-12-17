package com.natanhp.football_league.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamModel(

    val id: Long?,

    @SerializedName("idTeam")
    val idTeam: String,

    @SerializedName("strTeamBadge")
    val teamLogo: String,

    @SerializedName("strTeam")
    val teamName: String,

    @SerializedName("strAlternate")
    val alternateName: String,

    @SerializedName("intFormedYear")
    val formedYear: String,

    @SerializedName("strStadium")
    val stadium: String,

    @SerializedName("strStadiumDescription")
    val stadiumDescription: String,

    @SerializedName("strStadiumLocation")
    val stadiumLocation: String,

    @SerializedName("strDescriptionEN")
    val teamDescription: String,

    @SerializedName("strSport")
    val sportType: String
) : Parcelable {
    companion object {
        const val TABLE_NAME = "TEAM_FAVORITE"
        const val ID = "ID_"
        const val TEAM_ID = "TEAM_ID"
        const val TEAM_LOGO = "TEAM_LOGO"
        const val TEAM_NAME = "TEAM_NAME"
        const val ALTERNATE_NAME = "ALTERNATE_NAME"
        const val FORMED_YEAR = "FORMED_YEAR"
        const val STADIUM = "STADIUM"
        const val STADIUM_DESCRIPTION = "STADIUM_DESCRIPTION"
        const val STADIUM_LOCATION = "STADIUM_LOCATION"
        const val TEAM_DESCRIPTION = "TEAM_DESCRIPTION"
        const val SPORT_TYPE = "SPORT_TYPE"
    }
}