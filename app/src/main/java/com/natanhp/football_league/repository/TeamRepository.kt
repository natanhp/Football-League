package com.natanhp.football_league.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.natanhp.football_league.api.RetrofitService
import com.natanhp.football_league.dao.TeamFavoriteDAO
import com.natanhp.football_league.endpoint.TeamEndpoint
import com.natanhp.football_league.model.TeamModel
import com.natanhp.football_league.model.TeamModels
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamRepository {
    private val team = MutableLiveData<ArrayList<TeamModel>>()
    private val teamList = MutableLiveData<TeamModels>()
    private val searchTeam = MutableLiveData<List<TeamModel>>()
    private val favoriteTeams = MutableLiveData<List<TeamModel>>()
    private val favoriteTeamsState = MutableLiveData<List<TeamModel>>()

    private val retrofit2 = RetrofitService.getRetrofitInstance()

    fun getTeamDetail(id: Int): LiveData<ArrayList<TeamModel>> {

        val endpoint = retrofit2.create(TeamEndpoint::class.java)
        endpoint.getTeamDetail(id).enqueue(object : Callback<TeamModels> {

            override fun onResponse(call: Call<TeamModels>, response: Response<TeamModels>) {
                team.postValue(response.body()?.teams)
            }

            override fun onFailure(call: Call<TeamModels>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

        return team
    }

    fun getTeamList(leagueId: Int): LiveData<TeamModels> {
        val endpoint = retrofit2.create(TeamEndpoint::class.java)
        endpoint.getAllTeams(leagueId).enqueue(object : Callback<TeamModels> {
            override fun onFailure(call: Call<TeamModels>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<TeamModels>, response: Response<TeamModels>) {
                response.body().let {
                    teamList.postValue(it)
                }
            }

        })

        return teamList
    }

    fun searchTeam(query: CharSequence): LiveData<List<TeamModel>> {
        val endpointSearch = retrofit2.create(TeamEndpoint::class.java)
        endpointSearch.searchTeam(query).enqueue(object : Callback<TeamModels> {
            override fun onFailure(call: Call<TeamModels>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<TeamModels>, response: Response<TeamModels>) {
                response.body()?.let {
                    val team: List<TeamModel> = it.teams
                    val temp = team.filter { s -> s.sportType == "Soccer" }

                    searchTeam.postValue(temp)
                }

            }

        })

        return searchTeam
    }

    fun insetFavorite(context: Context, team: TeamModel) {

        doAsync {
            TeamFavoriteDAO.addToFavorite(context, team)
        }
    }

    fun showFavorite(context: Context): LiveData<List<TeamModel>> {

        doAsync {
            favoriteTeams.postValue(TeamFavoriteDAO.showFavorite(context))
        }

        return favoriteTeams
    }

    fun removeFromFavorite(context: Context, teamId: Long) {
        doAsync {
            TeamFavoriteDAO.removeFromFavorite(context, teamId)
        }
    }

    fun getFavoriteState(context: Context, teamId: Long): LiveData<List<TeamModel>> {
        doAsync {
            favoriteTeamsState.postValue(TeamFavoriteDAO.getFavoriteState(context, teamId))
        }

        return favoriteTeamsState
    }
}