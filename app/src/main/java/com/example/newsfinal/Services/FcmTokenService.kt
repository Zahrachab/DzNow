package com.example.newsfinal.Services

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.telephony.TelephonyManager
import com.example.newsfinal.Interface.ServiceInterface
import org.json.JSONObject

class FcmTokenService (public var context: Context){

    public fun saveToken(token: String) {
        val path: String = "tokenPost.php"

        val params = JSONObject()
        params.put("token", token)
        params.put("imei", getUniqueIMEIId(context))

        val service: ServiceInterface = ServiceVolley()
        service.post(path, params) { response ->
             val prefs = SharedPreferncesHelper(context)
            prefs.fcmToken = token
        }
    }

    fun getUniqueIMEIId(context: Context): String {
        try {
            val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return ""
            }
            val imei = telephonyManager.deviceId
            return if (imei != null && !imei.isEmpty()) {
                imei
            } else {
                android.os.Build.SERIAL
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return "not_found"
    }
}