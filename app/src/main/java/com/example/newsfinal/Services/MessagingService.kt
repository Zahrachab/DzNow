package com.example.newsfinal.Services

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.example.newsfinal.Interface.ServiceInterface
import com.example.newsfinal.Model.Article
import com.example.newsfinal.R
import com.example.newsfinal.View.NewsDetail
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import org.json.JSONObject

class MessagingService : FirebaseMessagingService() {
    private val TOPIC_GLOBAL = "global"

    /**
     * Récéption du message provenant du FCM
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        // Check if message contains a data payload.
        var id : String ?= null
        if (remoteMessage?.data?.size!! > 0) {
            id = remoteMessage.data["idArticle"]
            //généger une notification
            remoteMessage.notification?.getBody()?.let { remoteMessage.notification?.title?.let { it1 ->
                id?.let { it2 ->
                        sendNotification(it,
                            it1, it2
                        )
                    }
            } }
        }
        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {

        }

    }

    // Afficher push notification
    private fun sendNotification(messageBody: String, messageTitle: String,idArticle: String) {
        var article : Article ?= null
        val service: ServiceInterface = ServiceVolley()
        val path = "getArticle.php?id=$idArticle"
        service.get(path) { response ->
            if(response != null && response != "error" && response!= "")
            {
                val gson = Gson()
                val jsonObject = JSONObject(response)
                if (jsonObject != null) {
                    val article = gson.fromJson(jsonObject.toString(), Article::class.java)
                    val intent = Intent(this, NewsDetail::class.java)
                    NewsDetail.article = article
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    val pendingIntent = PendingIntent.getActivity(this, 0, intent,
                        PendingIntent.FLAG_ONE_SHOT)

                    val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                    val notificationBuilder = NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.icon_fcm)
                        .setContentTitle(messageTitle)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(soundUri)
                        .setContentIntent(pendingIntent)

                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.notify(0, notificationBuilder.build())
                }
            }
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
           val tokenService = FcmTokenService(this)
            tokenService.saveToken(token)

        }


        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the

    }




}