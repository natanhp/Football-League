package com.natanhp.football_league.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.natanhp.football_league.model.TeamModel
import com.natanhp.football_league.repository.TeamRepository
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: TeamRepository

    @Mock
    private lateinit var observer: Observer<ArrayList<TeamModel>>

    @Mock
    private lateinit var application: Application

    private lateinit var viewModel: TeamViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TeamViewModel(application)
        viewModel.getDetailTeam(134778).observeForever(observer)
    }

    @Test
    fun getDetailTeam() {
        viewModel.getDetailTeam(134778).observeForever(observer)
        Mockito.`when`(repository.getTeamDetail(134778)).thenReturn(null)
        assertNotNull(viewModel.getDetailTeam(134778))
        assertTrue(viewModel.getDetailTeam(134778).hasObservers())
    }
}