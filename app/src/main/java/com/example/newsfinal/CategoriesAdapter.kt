package com.example.newsfinal

import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.newsfinal.Interface.ServiceInterface
import com.example.newsfinal.Services.ServiceVolley
import com.example.newsfinal.Singleton.BackendVolley
import com.google.gson.Gson

class CategoriesAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm){

    // changer le fragement pour chaque tab dans le tablayout
    override fun getItem(position: Int): Fragment? {
        return ViewPagerFragment.newInstance(position)
    }

    // this counts total number of tabs
    override fun getCount(): Int = 5

}