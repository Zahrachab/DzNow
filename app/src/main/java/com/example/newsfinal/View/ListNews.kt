package com.example.newsfinal.View

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.RecyclerView
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import com.example.newsfinal.Adapters.ListNewsAdapter
import com.example.newsfinal.Interface.ServiceInterface
import com.example.newsfinal.Model.News
import com.example.newsfinal.R
import com.example.newsfinal.Services.FcmTokenService
import com.example.newsfinal.Services.MessagingService
import com.example.newsfinal.Services.ServiceVolley
import com.example.newsfinal.Services.SharedPreferncesHelper
import com.example.newsfinal.View.NewsDetail
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.Gson


import kotlinx.android.synthetic.main.fragment_list_news.*
import org.json.JSONArray
import org.json.JSONObject

class ListNews : Fragment() {

    private var listOfNews : List<News>? = listOf()
    private var mAdapter: ListNewsAdapter?= null
    private var categorie: Int = 0

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            retainInstance = true
            checFcmToken()


        }

    private fun checFcmToken() {
        var token1 : String ?= null

        var prefs = context?.let { SharedPreferncesHelper(it) }

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                token1 = task.result?.token.toString()
                if( (prefs?.fcmToken == "") || (token1 != prefs?.fcmToken))
                {
                    val tokenService = context?.let { FcmTokenService(it) }
                    tokenService?.saveToken(token1.toString())
                }


            })


    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.fragment_list_news, container, false)
        }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            list_recycler_view.apply {
                layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
                adapter = ListNewsAdapter(
                    listOfNews,
                    { partItem: News -> partItemClicked(partItem) })
                mAdapter = adapter as ListNewsAdapter
                }
            getListNews(categorie)
        }

    fun getListNews(categorie: Int) {
        val service: ServiceInterface = ServiceVolley()
        var path = ""

        if(categorie == 0)
            path = "newsGet.php"
        else {
            var ctg = ""
            when (categorie) {
                1 -> {
                    ctg = "1"
                }
                2 -> {
                  ctg = "2"
                }
                3 -> {
                    ctg = "3"
                }
                4 -> {
                    ctg = "4"
                }
            }
            path = "newsGetCategorie.php?categorie=" + ctg
        }
        var list = listOf<News>()
        service.get(path) { response ->
            if(response != null && response != "error" && response!= "")
            {
                val gson = Gson()
                val jsonArray = JSONArray(response)
                if (jsonArray != null) {
                    val list = gson.fromJson(jsonArray.toString(), Array<News>::class.java)
                    if (list!= null && list?.size != 0) {
                        listOfNews= list.toMutableList()
                        mAdapter?.refreshAdapter(listOfNews as MutableList<News>)
                    }
                }
            }
        }
    }


    private fun partItemClicked(partItem : News) {
        Toast.makeText(this.context, "Titre: ${partItem.title}", Toast.LENGTH_LONG).show()

        // Launch second activity, pass part ID as string parameter
        val showDetailActivityIntent = Intent(this.context, NewsDetail::class.java)
        NewsDetail.article = partItem
        startActivity(showDetailActivityIntent)
    }


    companion object {
            fun newInstance(categorie: Int) :
                    ListNews {
                val fragment = ListNews()
                fragment.categorie = categorie
                return fragment
            }
        }

}
