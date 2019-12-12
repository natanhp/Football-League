package com.natanhp.football_league.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.natanhp.football_league.model.LeagueModel
import com.natanhp.football_league.repository.LeagueRepository
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LeagueViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<LeagueModel>

    @Mock
    private lateinit var repository: LeagueRepository

    private lateinit var viewModel: LeagueViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = LeagueViewModel()
        viewModel.leagueDetail(4328).observeForever(observer)

    }


    @Test
    fun leagueDetail() {
        Mockito.`when`(repository.requestLeagueDetail(4328)).thenReturn(null)
        assertNotNull(viewModel.leagueDetail(4328))
        assertTrue(viewModel.leagueDetail(4328).hasObservers())
    }
}