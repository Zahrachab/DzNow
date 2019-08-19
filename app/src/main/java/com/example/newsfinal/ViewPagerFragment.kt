package com.example.newsfinal

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ViewPagerFragment : Fragment() {
    private var listOfNews : List<News>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root =inflater.inflate(com.example.newsfinal.R.layout.fragment_view_pager, container, false)
        val childFragment = ListNews.newInstance(listOfNews)
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(com.example.newsfinal.R.id.fragment_container_list, childFragment).commit()
        return root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(list: List<News>): ViewPagerFragment {
            val fragment = ViewPagerFragment()
            fragment.listOfNews = list
            return fragment
        }
    }
}