package com.example.newsfinal.View

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.example.newsfinal.Adapters.CategoriesAdapter
import com.example.newsfinal.R







class NewsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_news, container, false)

        val tabLayout = root.findViewById(R.id.tablayout) as TabLayout
        val viewPager = root.findViewById(R.id.viewPager) as ViewPager
        tabLayout.setupWithViewPager(viewPager)

        viewPager.visibility = View.GONE
        viewPager.offscreenPageLimit = 0

        // Important: Must use the child FragmentManager or you will see side effects.
        viewPager.adapter = CategoriesAdapter(childFragmentManager)
        viewPager.currentItem = 0

        viewPager.visibility = View.VISIBLE

        tabLayout.tabGravity = TabLayout.GRAVITY_CENTER;
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE;


        return root
    }


}