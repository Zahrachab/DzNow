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
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import com.bumptech.glide.Glide
import com.example.newsfinal.R
import com.google.firebase.auth.FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*
import android.widget.TextView
import com.google.android.gms.auth.api.Auth


class NewsActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (p0.itemId) {
            R.id.nav_signOut -> {
                signOut()
            }
            R.id.nav_options -> {
                bottomNavigation?.visibility = View.GONE
                val fragment = SettingsFragement()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_changing, fragment, fragment.javaClass.getSimpleName())
                    .commit()

            }

            R.id.nav_accueil -> {
                bottomNavigation?.visibility = View.VISIBLE
                val fragment = NewsFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_changing, fragment, fragment.javaClass.getSimpleName())
                    .commit()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    var bottomNavigation: BottomNavigationView? = null
    var mTopToolbar: Toolbar? = null
    var firebaseAuth : FirebaseAuth ?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()

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

        Glide.with(this).load(firebaseAuth?.currentUser?.photoUrl).into(photo)
        userName.text = firebaseAuth?.currentUser?.displayName

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

        bottomNavigation?.visibility = View.VISIBLE

        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                // fragement des articles
                R.id.homeId -> {
                    bottomNavigation?.visibility = View.VISIBLE
                    val fragment = NewsFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_changing, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }

                //fragement des articles sauvegardés
                R.id.archiveId-> {
                    bottomNavigation?.visibility = View.VISIBLE
                    var fragment  = SauvegaderFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_changing, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
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

    private fun signOut() {
        // Firebase sign out
        firebaseAuth?.signOut()

        // Google sign out
        Auth.GoogleSignInApi.signOut(Accueil.googleApiClient)
        intent = Intent (this, Accueil::class.java)
        startActivity(intent)
    }


}