package com.natanhp.football_league.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.natanhp.football_league.model.MatchModels
import com.natanhp.football_league.modeldata.MatchModel
import com.natanhp.football_league.repository.MatchRepository
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var observer: Observer<MatchModels>

    @Mock
    private lateinit var observerSearch: Observer<List<MatchModel>>

    @Mock
    private lateinit var matchRepository: MatchRepository

    private lateinit var viewModel: MatchViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MatchViewModel(application)
    }

    @Test
    fun getNextMatches() {
        viewModel.getNextMatches(4328).observeForever(observer)
        Mockito.`when`(matchRepository.getNextMatches(4328)).thenReturn(null)
        assertNotNull(viewModel.getNextMatches(4328))
        assertTrue(viewModel.getNextMatches(4328).hasObservers())
    }

    @Test
    fun getPrevMatches() {
        viewModel.getPrevMatches(4328).observeForever(observer)
        Mockito.`when`(matchRepository.getPrevMatches(4328)).thenReturn(null)
        assertNotNull(viewModel.getPrevMatches(4328))
        assertTrue(viewModel.getPrevMatches(4328).hasObservers())
    }

    @Test
    fun searchMatches() {
        viewModel.searchMatches("England").observeForever(observerSearch)
        Mockito.`when`(matchRepository.searchMatches("England")).thenReturn(null)
        assertNotNull(viewModel.searchMatches("England"))
        assertTrue(viewModel.searchMatches("England").hasObservers())
    }

    @Test
    fun getMatchDetail() {
        viewModel.getMatchDetail(602290).observeForever(observer)
        Mockito.`when`(matchRepository.getMatchDetail(602290)).thenReturn(null)
        assertNotNull(viewModel.getMatchDetail(602290))
        assertTrue(viewModel.getMatchDetail(602290).hasObservers())
    }
}