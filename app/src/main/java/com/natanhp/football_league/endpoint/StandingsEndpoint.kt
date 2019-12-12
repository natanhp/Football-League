package com.natanhp.football_league.endpoint

import com.natanhp.football_league.model.StandingsModels
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StandingsEndpoint {
    @GET("api/v1/json/1/lookuptable.php")
    fun getStandings(@Query("l") leagueId: Int): Call<StandingsModels>
}