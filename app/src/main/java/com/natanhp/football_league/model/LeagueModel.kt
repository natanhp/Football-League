package com.natanhp.football_league.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueModel(
    @SerializedName("idLeague")
    private val id: Int,

    @SerializedName("strLeague")
    private val name: String,

    private val logo: Int,

    @SerializedName("strBadge")
    private val onlineLogo: String,

    @SerializedName("strDescriptionEN")
    private val description: String
) : Parcelable {
    fun getId() = id
    fun getName() = name
    fun getLogo() = logo
    fun getDesc() = description
    fun getOnlineLogo() = onlineLogo
}