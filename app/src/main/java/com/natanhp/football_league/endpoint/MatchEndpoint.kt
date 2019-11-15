package com.natanhp.football_league.endpoint

import com.natanhp.football_league.model.MatchModels
import com.natanhp.football_league.model.MatchModelsSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MatchEndpoint {

    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextMatches(@Query("id") id: Int?): Call<MatchModels>

    @GET("api/v1/json/1/eventspastleague.php")
    fun getPreviousMatches(@Query("id") id: Int?): Call<MatchModels>

    @GET("api/v1/json/1/searchevents.php")
    fun searchMatches(@Query("e") e: String): Call<MatchModelsSearch>

    @GET("api/v1/json/1/lookupevent.php?")
    fun getDetailMatch(@Query("id") matchID: Int?): Call<MatchModels>
}