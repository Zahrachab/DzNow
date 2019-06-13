package com.example.newsfinal

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_fragement.*



class NewsActivity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var bottomNavigation: BottomNavigationView? = null


    fun onCreateFragementTopNews()= TopNewsFragement.newInstance()
    fun onCreateFragementBottomNavigation() = BottomNavigation.newInstance()
    fun onCreateFragementToolbar() = ToolbarFragment.newInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragement)

        tabLayout = findViewById<TabLayout>(R.id.tablayout)
        viewPager = findViewById<ViewPager>(R.id.viewPager)
        bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView3)


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
                viewPager!!.currentItem = tab.position
            }
        })

        val fm = supportFragmentManager

        var fragment1 = fm.findFragmentById(R.id.fragment_top_news)
        var fragment2 = fm.findFragmentById(R.id.fragment_toolbar)
        var fragment3 = fm.findFragmentById(R.id.fragment_bottomNavigation)


        // ensures fragments already created will not be created
        if (fragment1 == null) {
            fragment1 = onCreateFragementTopNews()
            // create and commit a fragment transaction
            fm.beginTransaction()
                .add(R.id.fragment_top_news, fragment1)
                .commit()
        }

        if (fragment2 == null) {
            fragment2 = onCreateFragementToolbar()
            // create and commit a fragment transaction
            fm.beginTransaction()
                .add(R.id.fragment_toolbar, fragment2)
                .commit()
        }


        /*if (fragment3 == null) {
            fragment3 = onCreateFragementBottomNavigation()
            // create and commit a fragment transaction
            fm.beginTransaction()
                .add(R.id.fragment_bottomNavigation, fragment3)
                .commit()
        }*/


        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeId-> {
                    val intent = Intent(this, NewsActivity::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.addArticleId-> {
                    val intent = Intent(this, NewsActivity::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.archiveId-> {
                    val intent = Intent(this, ArchiveActivity::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

        bottomNavigation!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)



    }
}