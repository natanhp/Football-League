package com.natanhp.football_league.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.natanhp.football_league.R
import com.natanhp.football_league.api.RetrofitService
import com.natanhp.football_league.endpoint.LeagueEndpoint
import com.natanhp.football_league.model.LeagueModel
import com.natanhp.football_league.model.LeaguesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueRepository() {
    private val ids = listOf(4328, 4334, 4331, 4332, 4335, 4346, 4344, 4356, 4330, 4396)
    private val names = listOf(
        "English Premier League",
        "French Ligue 1",
        "German Bundesliga",
        "Italian Serie A",
        "Spanish La Liga",
        "American Mayor League",
        "Protugeuese Premiera Liga",
        "Australian A League",
        "Scotish Premier League",
        "English League 1"
    )

    private val images = listOf(
        R.drawable.english_premier_league_1,
        R.drawable.french_ligue_1_2,
        R.drawable.german_bundesliga_3,
        R.drawable.italian_serie_a_4,
        R.drawable.spanish_la_liga_5,
        R.drawable.american_mayor_league_6,
        R.drawable.portugeuese_premiera_liga_7,
        R.drawable.australian_a_league_8,
        R.drawable.scotish_premier_league_9,
        R.drawable.english_league_1_10
    )

    private val leagueDetail = MutableLiveData<LeagueModel>()


    fun getLeague(): LiveData<ArrayList<LeagueModel>> {
        val data = MutableLiveData<ArrayList<LeagueModel>>()
        val item = ArrayList<LeagueModel>()

        ids.forEachIndexed { index, i ->
            item.add(LeagueModel(i, names[index], images[index], "", ""))
        }

        data.value = item

        return data
    }

    fun requestLeagueDetail(id: Int): LiveData<LeagueModel> {
        val retrofit2 = RetrofitService.getRetrofitInstance()
        val leagueEnpoint = retrofit2.create(LeagueEndpoint::class.java)

        leagueEnpoint.getDetailLeague(id).enqueue(object : Callback<LeaguesModel> {
            override fun onFailure(call: Call<LeaguesModel>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<LeaguesModel>, response: Response<LeaguesModel>) {
                leagueDetail.postValue(response.body()?.getLeageuModels()?.get(0))
            }

        })

        return leagueDetail
    }
}