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
import android.support.constraint.ConstraintLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.text.Layout
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.newsfinal.R
import com.google.firebase.auth.FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*
import android.widget.TextView
import com.bumptech.glide.load.engine.Resource
import kotlinx.android.synthetic.main.nav_header_main.*
import android.widget.LinearLayout







class NewsActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var bottomNavigation: BottomNavigationView? = null
    var mTopToolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTopToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(mTopToolbar)
        getSupportActionBar()?.setTitle(null)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)




        val navigationView =  findViewById<NavigationView>(R.id.nav_view)

        val photo= navigationView.getHeaderView(0).findViewById<CircleImageView>(R.id.profile_image)
        val userName= navigationView.getHeaderView(0).findViewById<TextView>(R.id.userNameHeader)
        val firebaseAuth = FirebaseAuth.getInstance()
        Glide.with(this).load(firebaseAuth?.currentUser?.photoUrl).into(photo)
        userName.text = firebaseAuth.currentUser?.displayName

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