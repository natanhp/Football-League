package com.natanhp.football_league.viewmodel

import com.natanhp.football_league.repository.StandingsRepository

class StandingsViewModel {
    private val standingRepository = StandingsRepository()

    fun getStandings(leagueId: Int) = standingRepository.getStandings(leagueId)
}