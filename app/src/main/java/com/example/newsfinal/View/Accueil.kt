
package com.example.newsfinal.View

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.newsfinal.R
import kotlinx.android.synthetic.main.activity_accueil.*
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.telephony.TelephonyManager
import android.widget.Toast
import com.example.newsfinal.Interface.ServiceInterface
import com.example.newsfinal.Services.ServiceVolley
import com.google.firebase.iid.FirebaseInstanceId
import org.json.JSONObject



class Accueil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accueil)

        buttonStart.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { instanceIdResult ->
            val deviceToken = instanceIdResult.token
            saveToken(deviceToken)
        }
    }


    fun saveToken(token: String) {
        val path: String = "tokenPost.php"

        val params = JSONObject()
        params.put("imei", getUniqueIMEIId(this))
        params.put("token", token)

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