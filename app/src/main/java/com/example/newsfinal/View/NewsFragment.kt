package com.example.newsfinal.View

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.example.newsfinal.R


class NewsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_news, container, false)

        val viewPager = root.findViewById(R.id.viewPager) as ViewPager

        // Important: Must use the child FragmentManager or you will see side effects.
        viewPager.adapter = CategoriesAdapter(childFragmentManager)

        val tab = root.findViewById<TabLayout>(R.id.tablayout)
        tab.setupWithViewPager(viewPager)
        tab.getTabAt(0)!!.setText(getResources().getText(R.string.item1))
        tab.getTabAt(1)!!.setText(getResources().getText(R.string.item2))
        tab.getTabAt(2)!!.setText(getResources().getText(R.string.item3))
        tab.getTabAt(3)!!.setText(getResources().getText(R.string.item4))
        tab.getTabAt(4)!!.setText(getResources().getText(R.string.item5))
        tab.setTabGravity(TabLayout.GRAVITY_CENTER);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);

        return root
    }


}