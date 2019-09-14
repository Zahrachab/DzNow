package com.example.newsfinal.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.newsfinal.R
import com.google.firebase.auth.FirebaseAuth

class UserActivity : AppCompatActivity() {

    val TAG = "UserActivity"
    // Firebase Auth Object.
    var firebaseAuth: FirebaseAuth? = null
    lateinit var ivProfilePicture: ImageView
    lateinit var tvName: TextView
    lateinit var tvEmail: TextView
    lateinit var tvUserId: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        firebaseAuth = FirebaseAuth.getInstance()
        ivProfilePicture = findViewById<View>(R.id.iv_profile) as ImageView
        tvName = findViewById<View>(R.id.tv_name) as TextView
        tvEmail = findViewById<View>(R.id.tv_email) as TextView
        tvUserId = findViewById<View>(R.id.tv_id)as TextView
        val user = firebaseAuth?.currentUser
        Log.i(TAG, "User account ID ${user?.uid}")
        Log.i(TAG, "Display Name : ${user?.displayName}")
        Log.i(TAG, "Email : ${user?.email}")
        Log.i(TAG, "Photo URL : ${user?.photoUrl}")
        Log.i(TAG, "Provider ID : ${user?.providerId}")
        tvName.text = user?.displayName
        tvEmail.text = user?.email
        tvUserId.text = user?.uid
        Glide.with(this@UserActivity)
            .load(user?.photoUrl)
            .into(ivProfilePicture)
    }
}
