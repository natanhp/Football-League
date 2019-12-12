package com.natanhp.football_league.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.natanhp.football_league.api.RetrofitService
import com.natanhp.football_league.endpoint.StandingsEndpoint
import com.natanhp.football_league.model.StandingsModels
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StandingsRepository {
    private val standings = MutableLiveData<StandingsModels>()
    private val retrofit2 = RetrofitService.getRetrofitInstance()

    fun getStandings(leagueId: Int): LiveData<StandingsModels> {
        val standingsEndpoint = retrofit2.create(StandingsEndpoint::class.java)

        standingsEndpoint.getStandings(leagueId).enqueue(object : Callback<StandingsModels> {
            override fun onFailure(call: Call<StandingsModels>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<StandingsModels>,
                response: Response<StandingsModels>
            ) {
                response.let {
                    standings.postValue(it.body())
                }
            }

        })

        return standings
    }
}