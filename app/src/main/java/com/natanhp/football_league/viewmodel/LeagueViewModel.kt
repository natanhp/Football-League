package com.natanhp.football_league.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.natanhp.football_league.model.LeagueModel
import com.natanhp.football_league.repository.LeagueRepository

class LeagueViewModel : ViewModel() {
    private val leagueRepository = LeagueRepository()
    val leagueModel: LiveData<ArrayList<LeagueModel>> = leagueRepository.getLeague()

    fun leagueDetail(id: Int) = leagueRepository.requestLeagueDetail(id)
}