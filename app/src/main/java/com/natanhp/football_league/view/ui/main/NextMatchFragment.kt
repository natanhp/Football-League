package com.natanhp.football_league.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.natanhp.football_league.R
import com.natanhp.football_league.adapter.MatchAdapter
import com.natanhp.football_league.model.LeagueModel
import com.natanhp.football_league.view.MatchDetailActivity
import com.natanhp.football_league.viewmodel.MatchViewModel
import org.jetbrains.anko.support.v4.startActivity

/**
 * A placeholder fragment containing a simple view.
 */
class NextMatchFragment : Fragment() {

    private lateinit var matchViewModel: MatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.match_recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = MatchAdapter {
            startActivity<MatchDetailActivity>("match_detail" to it)
        }

        recyclerView.adapter = adapter

        val league = activity?.intent?.getParcelableExtra<LeagueModel>("match")

        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)

        matchViewModel.getNextMatches(league?.getId()).observe(viewLifecycleOwner, Observer {
            progressBar.visibility = View.VISIBLE
            it?.let {
                progressBar.visibility = View.GONE
                it.getMatches()?.let { it1 -> adapter.setMatch(it1) }
            }
        })
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): NextMatchFragment {
            return NextMatchFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}