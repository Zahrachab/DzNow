package com.example.newsfinal.View

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.support.v7.widget.Toolbar
import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import com.example.newsfinal.R
import java.util.*



class NewsActivity : AppCompatActivity() {
    var bottomNavigation: BottomNavigationView? = null
    var mTopToolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragement)

        mTopToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(mTopToolbar)
        getSupportActionBar()!!.setTitle(null)

        val fm = supportFragmentManager

       var fragment1 = fm.findFragmentById(R.id.fragment_changing)


        // ensures fragments already created will not be created
        if (fragment1 == null) {
            fragment1 = NewsFragment()
            // create and commit a fragment transaction
            fm.beginTransaction()
                .add(R.id.fragment_changing, fragment1)
                .commit()
        }

        bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView3)


        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeId -> {
                    val fragment = NewsFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_changing, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.addArticleId -> {
                    val fragment = AddArticleFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_changing, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                /*R.id.archiveId-> {
                    var fragment: ListNews = ListNews.newInstance(getArchivedNews())
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_changing, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }*/
            }
            false
        }

        bottomNavigation!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.langue, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.item1) {
            return true
        }
        if (id == R.id.item2) {
            setLocate("fr")
            recreate()
            return true
        }
        if (id == R.id.item3) {
            setLocate("ar")
            recreate()
            return true
        }

        if(id == R.id.item4) {
            intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)

    }

    private fun setLocate(Lang: String) {

        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    private fun loadLocate() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        setLocate(language)
    }
}