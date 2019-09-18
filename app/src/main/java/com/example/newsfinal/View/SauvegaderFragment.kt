package com.example.newsfinal.View


import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.newsfinal.Adapters.ListNewsAdapter
import com.example.newsfinal.Interface.ServiceInterface
import com.example.newsfinal.Model.News
import com.example.newsfinal.R

import com.example.newsfinal.Room.AppTools
import com.example.newsfinal.Room.NewsDB
import com.example.newsfinal.Room.NewsDao
import com.example.newsfinal.Services.ServiceVolley
import com.google.firebase.auth.FirebaseAuth

import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_archive.*
import org.json.JSONArray



class SauvegaderFragment : Fragment() {
    private var listOfNews : List<News>? = listOf()
    private var mAdapter: ListNewsAdapter?= null
    private var db: NewsDB? = null
    private var dao: NewsDao? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
    }




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_archive, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        list_recycler_view_archived.apply {
            layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
            adapter = ListNewsAdapter(listOfNews, { partItem : News  -> partItemClicked(partItem) })
            mAdapter = adapter as ListNewsAdapter
        }

        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        if (isConnected) {
            getListNewsFromServer()
        } else {
            getListNewsFromLocal()
        }
    }



    private fun ynchronise

    private fun getListNewsFromLocal() {


        db = context?.let { NewsDB.getInstance(it) }
        dao = db?.articleDao()
        var list: List<News> = dao?.getNews()!!
        if (list != null && list?.size != 0) {
            listOfNews = list.toMutableList()
            mAdapter?.refreshAdapter(listOfNews as MutableList<News>)
        }

    }


    fun getListNewsFromServer() {
        val firebase = FirebaseAuth.getInstance()
        val service: ServiceInterface = ServiceVolley()
        var path = "getArchivedArticles.php?uid="+ firebase.currentUser?.uid

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
        fun newInstance() :SauvegaderFragment= SauvegaderFragment()
    }

}
