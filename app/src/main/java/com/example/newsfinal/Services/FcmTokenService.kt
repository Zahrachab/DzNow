package com.example.newsfinal.Services

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.telephony.TelephonyManager
import com.example.newsfinal.Interface.ServiceInterface
import com.example.newsfinal.Singleton.ImeiUser
import org.json.JSONObject

class FcmTokenService (public var context: Context){

    /**
     * Envoyer le FCM token au service web pour sauvegarde
     */
    public fun saveToken(token: String) {
        val path: String = "tokenPost.php"

        val params = JSONObject()
        params.put("token", token)
        params.put("imei", ImeiUser.getImei(context))

        val service: ServiceInterface = ServiceVolley()
        service.post(path, params) { response ->
             val prefs = SharedPreferncesHelper(context)
            prefs.fcmToken = token
        }
    }


}