package com.example.newsfinal

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment



import kotlinx.android.synthetic.main.fragment_list_news.*


abstract class SingleFragmentActivity : AppCompatActivity() {

    private val layoutResId: Int
        get() = R.layout.activity_fragement

    protected abstract fun createFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutResId)

        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.fragment_container)

        // ensures fragments already created will not be created
        if (fragment == null) {
            fragment = createFragment()
            // create and commit a fragment transaction
            fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }
}