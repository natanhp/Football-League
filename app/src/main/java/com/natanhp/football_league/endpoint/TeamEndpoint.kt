package com.natanhp.football_league.endpoint

import com.natanhp.football_league.model.TeamModels
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamEndpoint {
    @GET("api/v1/json/1/lookupteam.php")
    fun getTeamDetail(@Query("id") id: Int): Call<TeamModels>

    @GET("api/v1/json/1/lookup_all_teams.php")
    fun getAllTeams(@Query("id") leagueId: Int): Call<TeamModels>

    @GET("api/v1/json/1/searchteams.php")
    fun searchTeam(@Query("t") query: CharSequence): Call<TeamModels>
}