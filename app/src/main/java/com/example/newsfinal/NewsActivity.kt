package com.example.newsfinal

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager


import kotlinx.android.synthetic.main.fragment_list_news.*


abstract class NewsActivity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    private val layoutResId: Int
        get() = R.layout.activity_fragement

    abstract fun oncreateFragement() : Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)

        tabLayout = findViewById<TabLayout>(R.id.tablayout)
        viewPager = findViewById<ViewPager>(R.id.viewPager)


        val adapter = CategoriesAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = adapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


        val fm = supportFragmentManager
        /*var fragment1 = fm.findFragmentById(R.id.fragment_container)


        // ensures fragments already created will not be created
        if (fragment1 == null) {
            fragment1 = createFragment1()
            // create and commit a fragment transaction
            fm.beginTransaction()
                .add(R.id.fragment_container, fragment1)
                .commit()
        }*/


        var fragment2 = fm.findFragmentById(R.id.fragment_top_news)


        // ensures fragments already created will not be created
        if (fragment2 == null) {
            fragment2 = oncreateFragement()
            // create and commit a fragment transaction
            fm.beginTransaction()
                .add(R.id.fragment_top_news, fragment2)
                .commit()
        }
    }
}