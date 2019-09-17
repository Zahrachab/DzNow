
package com.example.newsfinal.View

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.telephony.TelephonyManager
import com.example.newsfinal.R
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.newsfinal.Interface.ServiceInterface
import com.example.newsfinal.Services.ServiceVolley
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.UserInfo
import com.google.firebase.iid.FirebaseInstanceId
import org.json.JSONObject


class Accueil : AppCompatActivity(), View.OnClickListener {


    val TAG = "CreateAccount"
    //Init views
    lateinit var googleSignInButton: SignInButton

    //Request codes
    val GOOGLE_LOG_IN_RC = 1
    val FACEBOOK_LOG_IN_RC = 2



    override fun onStart() {
        super.onStart()
        if (googleApiClient != null) {
            googleApiClient?.connect()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accueil)
        firebaseAuth = FirebaseAuth.getInstance()
        if(firebaseAuth?.currentUser != null)
        {
            val intent : Intent = Intent (this, NewsActivity::class.java)
			
            startActivity(intent)

        }
        else {
            googleSignInButton = findViewById<View>(R.id.google_sign_in_button) as SignInButton

            googleSignInButton.setOnClickListener(this@Accueil)

            // Configure Google Sign In
            val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.request_client_id))
                .requestEmail()
                .build()
            // Creating and Configuring Google Api Client.
            googleApiClient = GoogleApiClient.Builder(this@Accueil)
                .enableAutoManage(this@Accueil  /* OnConnectionFailedListener */) { }
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build()
        }


        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

            })




    }


    private fun googleLogin() {
        Log.i(TAG, "Starting Google LogIn Flow.")
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(signInIntent, GOOGLE_LOG_IN_RC)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.google_sign_in_button -> {
                Log.i(TAG, "Trying Google LogIn.")
                googleLogin()
            }

        }
    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i(TAG, "Got Result code ${requestCode}.")
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == GOOGLE_LOG_IN_RC) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            Log.i(TAG, "With Google LogIn, is result a success? ${result.isSuccess}.")
            if (result.isSuccess) {
                // Google Sign In was successful, authenticate with Firebase
                firebaseAuthWithGoogle(result.signInAccount!!)
            } else {
                Toast.makeText(this@Accueil, "Some error occurred.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.i(TAG, "Authenticating user with firebase.")
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth?.signInWithCredential(credential)?.addOnCompleteListener(this) { task ->
            Log.i(TAG, "Firebase Authentication, is result a success? ${task.isSuccessful}.")
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information


                startActivity(Intent(this@Accueil, NewsActivity::class.java))
            } else {
                // If sign in fails, display a message to the user.
                Log.e(TAG, "Authenticating with Google credentials in firebase FAILED !!")
            }
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

    private fun saveUserInfos() {
        val path: String = "saveCompte.php"

        val params = JSONObject()
        params.put("uid", firebaseAuth?.currentUser?.uid)
        params.put("imei",getUniqueIMEIId(this))
        params.put("name", firebaseAuth?.currentUser?.displayName)

        val service: ServiceInterface = ServiceVolley()
        service.post(path, params) { response ->
            Toast.makeText(this, "Modification avec succès", Toast.LENGTH_SHORT).show()

        }
    }

    companion object {
        // Google API Client object.
        var googleApiClient: GoogleApiClient? = null
        // Firebase Auth Object.
        var firebaseAuth: FirebaseAuth? = null
    }


}