package com.natanhp.football_league.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.natanhp.football_league.repository.MatchRepository

class MatchViewModel(application: Application) : AndroidViewModel(application) {
    private val matchRepository = MatchRepository()

    fun getNextMatches(id: Int?) = matchRepository.getNextMatches(id)
    fun getPrevMatches(id: Int?) = matchRepository.getPrevMatches(id)
    fun searchMatches(query: CharSequence) = matchRepository.searchMatches(query)
    fun showFavoriteMatches(context: Context) = matchRepository.showFavorite(context)
    fun getMatchDetail(matchID: Int?) = matchRepository.getMatchDetail(matchID)
}