package com.natanhp.football_league.viewmodel

import androidx.lifecycle.ViewModel
import com.natanhp.football_league.repository.MatchRepository

class MatchViewModel : ViewModel() {
    private val matchRepository = MatchRepository()

    fun getNextMatches(id: Int?) = matchRepository.getNextMatches(id)
}