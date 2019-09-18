package com.example.newsfinal.Services

import android.content.Context
import android.content.SharedPreferences

class SharedPreferncesHelper (context: Context){

    private val PREFS_FILENAME = "com.teamtreehouse.colorsarefun.prefs"
    private val TOKEN = "fcmToken"
    private val GOOGLE = "google"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    //token généré par FCM
    var fcmToken: String
        get() = prefs.getString(TOKEN, "")
        set(value) = prefs.edit().putString(TOKEN, value).apply()

    //uid du compte utilisé
    var googleUid: String
        get() = prefs.getString(GOOGLE, "")
        set(value) = prefs.edit().putString(GOOGLE, value).apply()

}