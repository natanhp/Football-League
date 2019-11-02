package com.natanhp.football_league.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueModel(
    private val id: Int,
    private val name: String,
    private val logo: Int,
    private val description: String
) : Parcelable {
    fun getId() = id
    fun getName() = name
    fun getLogo() = logo
    fun getDesc() = description
}