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

class PrevMatchFragment : Fragment() {

    private lateinit var matchViewModel: MatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_match, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.match_recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = MatchAdapter {
            startActivity<MatchDetailActivity>("match_detail" to it)
        }

        recyclerView.adapter = adapter

        val league = activity?.intent?.getParcelableExtra<LeagueModel>("match")

        val progressBar = root.findViewById<ProgressBar>(R.id.progress_bar)

        matchViewModel.getPrevMatches(league?.getId()).observe(viewLifecycleOwner, Observer {
            progressBar.visibility = View.VISIBLE
            it?.let {
                progressBar.visibility = View.GONE
                adapter.setMatch(it.getMatches())
            }
        })

        return root
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
        fun newInstance(sectionNumber: Int): PrevMatchFragment {
            return PrevMatchFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}