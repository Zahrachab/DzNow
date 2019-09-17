package com.example.newsfinal.Adapters

import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.newsfinal.View.ListNews

class CategoriesAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm){


    // tab titles
    private val tabTitles = arrayOf("Pour Vous", "Sport", "Politique", "International", "Culture")


    // overriding getPageTitle()
    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }

    // changer le fragement pour chaque tab dans le tablayout
    override fun getItem(position: Int): Fragment? {

        return ListNews.newInstance(position)
    }


    override fun getItemPosition(`object`: Any): Int {
        // Causes adapter to reload all Fragments when
        // notifyDataSetChanged is called
        return 0
    }


    // this counts total number of tabs
    override fun getCount(): Int = 5

}