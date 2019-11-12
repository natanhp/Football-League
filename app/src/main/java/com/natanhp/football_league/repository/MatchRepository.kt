package com.natanhp.football_league.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.natanhp.football_league.api.RetrofitService
import com.natanhp.football_league.endpoint.MatchEndpoint
import com.natanhp.football_league.model.MatchModels
import com.natanhp.football_league.model.MatchModelsSearch
import com.natanhp.football_league.modeldata.MatchModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchRepository {
    private val nextMatches = MutableLiveData<MatchModels>()
    private val prevMatches = MutableLiveData<MatchModels>()
    private val searchMatches = MutableLiveData<List<MatchModel>>()

    private val retrofit2 = RetrofitService.getRetrofitInstance()

    fun getNextMatches(id: Int?): LiveData<MatchModels> {
        val nextMatchEndpoit = retrofit2.create(MatchEndpoint::class.java)

        nextMatchEndpoit.getNextMatches(id).enqueue(object : Callback<MatchModels> {
            override fun onFailure(call: Call<MatchModels>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MatchModels>, response: Response<MatchModels>) {
                nextMatches.postValue(response.body())
            }

        })

        return nextMatches
    }

    fun getPrevMatches(id: Int?): LiveData<MatchModels> {
        val nextMatchEndpoit = retrofit2.create(MatchEndpoint::class.java)

        nextMatchEndpoit.getPreviousMatches(id).enqueue(object : Callback<MatchModels> {
            override fun onFailure(call: Call<MatchModels>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MatchModels>, response: Response<MatchModels>) {
                prevMatches.postValue(response.body())
            }

        })

        return prevMatches
    }

    fun searchMatches(query: CharSequence): LiveData<List<MatchModel>> {
        val nextMatchEndpoit = retrofit2.create(MatchEndpoint::class.java)

        nextMatchEndpoit.searchMatches(query.toString())
            .enqueue(object : Callback<MatchModelsSearch> {
                override fun onFailure(call: Call<MatchModelsSearch>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<MatchModelsSearch>,
                    response: Response<MatchModelsSearch>
                ) {
                    val test: List<MatchModel>? = response.body()?.matches
                    val temp = test?.filter { s -> s.sportType == "Soccer" }

                    searchMatches.postValue(temp)
                }

            })

        return searchMatches
    }
}