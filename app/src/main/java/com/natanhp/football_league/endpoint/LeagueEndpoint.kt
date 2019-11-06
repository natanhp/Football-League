package com.natanhp.football_league.endpoint

import com.natanhp.football_league.model.LeaguesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LeagueEndpoint {
    @GET("api/v1/json/1/lookupleague.php?")
    fun getDetailLeague(@Query("id") id: Int): Call<LeaguesModel>
}