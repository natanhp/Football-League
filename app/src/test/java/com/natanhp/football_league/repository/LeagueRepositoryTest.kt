package com.natanhp.football_league.repository

import androidx.lifecycle.LiveData
import com.natanhp.football_league.model.LeagueModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LeagueRepositoryTest {

    @Mock
    private lateinit var leagueDetail: LiveData<LeagueModel>

    @Mock
    private lateinit var leagueRepository: LeagueRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun requestLeagueDetail() {
        val leagueId = 4328
        Mockito.`when`(leagueRepository.requestLeagueDetail(leagueId))
            .thenReturn(leagueDetail)
    }
}