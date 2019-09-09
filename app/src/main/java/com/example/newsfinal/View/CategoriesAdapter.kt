package com.example.newsfinal.View

import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class CategoriesAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm){

    // changer le fragement pour chaque tab dans le tablayout
    override fun getItem(position: Int): Fragment? {
        return ViewPagerFragment.newInstance(position)
    }

    // this counts total number of tabs
    override fun getCount(): Int = 5

}