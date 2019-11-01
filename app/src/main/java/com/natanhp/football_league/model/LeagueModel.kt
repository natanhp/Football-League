package com.natanhp.football_league.model

data class LeagueModel(
    private val id: Int,
    private val name: String,
    private val logo: Int,
    private val description: String
) {
    fun getId() = id
    fun getName() = name
    fun getLogo() = logo
    fun getDesc() = description
}