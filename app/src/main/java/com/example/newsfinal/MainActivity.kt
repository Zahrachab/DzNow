package com.example.newsfinal
import android.content.Intent
import android.support.v4.app.Fragment


class MainActivity : NewsActivity() {
    override fun oncreateFragement()= TopNewsFragement.newInstance()




}

