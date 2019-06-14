package com.example.newsfinal

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager


class NewsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(com.example.newsfinal.R.layout.fragment_news, container, false)

        val viewPager = root.findViewById(com.example.newsfinal.R.id.viewPager) as ViewPager

        // Important: Must use the child FragmentManager or you will see side effects.
        viewPager.adapter = CategoriesAdapter(childFragmentManager)

        val tab = root.findViewById<TabLayout>(com.example.newsfinal.R.id.tablayout)
        tab.setTabGravity(TabLayout.GRAVITY_CENTER);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);

        return root
    }


}