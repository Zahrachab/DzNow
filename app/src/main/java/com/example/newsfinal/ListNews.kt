package com.example.newsfinal


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager


import kotlinx.android.synthetic.main.fragment_list_news.*

class ListNews : Fragment() {

    private val mNicolasCageMovies = listOf(
        News("Raising Arizona", "loremidd djhferf jhrg jhgrig gtrhitg jgtihjt jgktjh jhtjht fgti", "28 Octobre 2019 "),
        News("Vampire's Kiss", "loremidd djhferf jhrg jhgrig gtrhitg jgtihjt jgktjh jhtjht fgti","28 Octobre 2019 "),
        News("Con Air", "loremidd djhferf jhrg jhgrig gtrhitg jgtihjt jgktjh jhtjht fgti","28 Octobre 2019 "),
        News("Gone in 60 Seconds", "loremidd djhferf jhrg jhgrig gtrhitg jgtihjt jgktjh jhtjht fgti","28 Octobre 2019 "),
        News("National Treasure", "loremidd djhferf jhrg jhgrig gtrhitg jgtihjt jgktjh jhtjht fgti","28 Octobre 2019 ")
    )

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            retainInstance = true
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_list_news, container, false)


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            list_recycler_view.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = ListNewsAdapter(mNicolasCageMovies)
            }
        }

        companion object {
            fun newInstance(): ListNews = ListNews()
        }
    }