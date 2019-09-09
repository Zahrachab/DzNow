package com.example.newsfinal.Services

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import com.example.newsfinal.Interface.ServiceInterface
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONObject

class MessagingService : FirebaseMessagingService() {
    private val TOPIC_GLOBAL = "global"


    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        // Check if message contains a data payload.
        if (remoteMessage?.data?.size!! > 0) {

        }

        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {


        }

    }


    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String?) {
        Log.d("", "Refreshed token: $token")

        // now subscribe to `global` topic to receive app wide notifications
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_GLOBAL)
        if (token != null) {
            saveToken(token)
        }


        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the

    }

    fun saveToken(token: String) {
        val path: String = "tokenPost.php"

        val params = JSONObject()
        params.put("token", token)
        params.put("imei", getUniqueIMEIId(this))

        val service: ServiceInterface = ServiceVolley()
        service.post(path, params) { response ->
            Toast.makeText(this, "Modification avec succès", Toast.LENGTH_SHORT).show()

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