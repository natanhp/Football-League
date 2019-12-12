package com.natanhp.football_league.viewmodel

import androidx.lifecycle.ViewModel
import com.natanhp.football_league.repository.StandingsRepository

class StandingsViewModel : ViewModel() {
    private val standingRepository = StandingsRepository()

    fun getStandings(leagueId: Int) = standingRepository.getStandings(leagueId)
}