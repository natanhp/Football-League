package com.natanhp.football_league.repository

import androidx.lifecycle.LiveData
import com.natanhp.football_league.model.MatchModels
import com.natanhp.football_league.modeldata.MatchModel
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchRepositoryTest {

    @Mock
    private lateinit var matchRepository: MatchRepository

    @Mock
    private lateinit var matchResponse: LiveData<MatchModels>

    @Mock
    private lateinit var searchResponse: LiveData<List<MatchModel>>

    private var leagueId = 0
    private var matchId = 0

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        leagueId = 4328
        matchId = 602279
    }

    @Test
    fun getNextMatches() {
        Mockito.`when`(matchRepository.getNextMatches(leagueId))
            .thenReturn(matchResponse)
    }

    @Test
    fun getPrevMatches() {
        Mockito.`when`(matchRepository.getPrevMatches(leagueId))
            .thenReturn(matchResponse)
    }

    @Test
    fun searchMatches() {
        Mockito.`when`(matchRepository.searchMatches(ArgumentMatchers.anyString()))
            .thenReturn(searchResponse)
    }

    @Test
    fun getMatchDetail() {
        Mockito.`when`(matchRepository.getMatchDetail(matchId))
            .thenReturn(matchResponse)
    }
}