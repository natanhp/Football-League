package com.natanhp.football_league.viewmodel

import androidx.lifecycle.ViewModel
import com.natanhp.football_league.repository.TeamRepository

class TeamViewModel : ViewModel(){
    private val teamRepository = TeamRepository()

    fun getDetailTeam(id: Int) = teamRepository.getTeamDetail(id)

}