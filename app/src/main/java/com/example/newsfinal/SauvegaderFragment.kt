package com.example.newsfinal


import android.content.Intent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.newsfinal.Room.AppTools
import com.example.newsfinal.Room.NewsDB
import com.example.newsfinal.Room.NewsDao

import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_archive.*
import org.json.JSONArray


class SauvegaderFragment : Fragment() {
    private var listOfNews : List<News>? = listOf()
    private var mAdapter: ListNewsAdapter ?= null
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




        getListNews()
    }




    private fun getListNews() {


        db = context?.let { NewsDB.getInstance(it) }
       dao = db?.articleDao()
        var list: List<News> = dao?.getNews()!!
        if (list != null && list?.size != 0) {
                    listOfNews = list.toMutableList()
                    mAdapter?.refreshAdapter(listOfNews as MutableList<News>)
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
