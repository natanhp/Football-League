package com.natanhp.football_league.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.natanhp.football_league.model.TeamModel
import com.natanhp.football_league.modeldata.MatchModel
import com.natanhp.football_league.repository.MatchRepository
import com.natanhp.football_league.repository.TeamRepository

class TeamViewModel(application: Application) : AndroidViewModel(application) {
    private val teamRepository = TeamRepository()
    private val matchRepository = MatchRepository()

    fun getDetailTeam(id: Int) = teamRepository.getTeamDetail(id)

    fun addToFavorite(context: Context, match: MatchModel) {
        matchRepository.insetFavorite(context, match)
    }

    fun removeFromFavorite(context: Context, matchID: Long) {
        matchRepository.removeFromFavorite(context, matchID)
    }

    fun getFavoriteState(context: Context, matchID: Long) =
        matchRepository.getFavoriteState(context, matchID)

    fun getTeamList(leagueId: Int) = teamRepository.getTeamList(leagueId)

    fun searchTeam(query: CharSequence) = teamRepository.searchTeam(query)

    fun showFavoriteTeams(context: Context) = teamRepository.showFavorite(context)

    fun addToFavoriteTeam(context: Context, team: TeamModel) {
        teamRepository.insetFavorite(context, team)
    }

    fun removeFromFavoriteTeam(context: Context, teamId: Long) {
        teamRepository.removeFromFavorite(context, teamId)
    }

    fun getFavoriteStateTeam(context: Context, teamId: Long) =
        teamRepository.getFavoriteState(context, teamId)

}