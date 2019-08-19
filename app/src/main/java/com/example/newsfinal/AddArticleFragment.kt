package com.example.newsfinal

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import com.example.newsfinal.R
import kotlinx.android.synthetic.main.fragment_add_article.*;
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.ButtonBarLayout
import android.widget.Button
import android.widget.ImageView


class AddArticleFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_article, container, false)
        val image = view.findViewById(R.id.image_view) as ImageView
        image.setOnClickListener {
            val permissionCheck = ContextCompat.checkSelfPermission(
                activity!!,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )

            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                startGallery()
            } else {
                ActivityCompat.requestPermissions(
                    activity!!,
                    arrayOf<String>(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    2000
                )
            }
        }
        return view
    }

    private fun startGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            image_view.setImageURI(data?.data)
        }
    }




}