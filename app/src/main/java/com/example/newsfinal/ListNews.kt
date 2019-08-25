package com.example.newsfinal

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.newsfinal.Interface.ServiceInterface
import com.example.newsfinal.Services.ServiceVolley
import com.google.firebase.database.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_list_news.*
import org.json.JSONArray

class ListNews : Fragment() {

    private var listOfNews : MutableList<News> = mutableListOf()
    private var mAdapter: ListNewsAdapter ?= null
    private var categorie: Int = 0
    private var database : FirebaseDatabase?= null
    private var myRef: DatabaseReference ?= null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            retainInstance = true
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.fragment_list_news, container, false)
        }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            list_recycler_view.apply {
                layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
                adapter = ListNewsAdapter(listOfNews, { partItem : News  -> partItemClicked(partItem) })
                mAdapter = adapter as ListNewsAdapter
                }

            database = FirebaseDatabase.getInstance()
            myRef = database?.getReference("news/news")

            // Read from the database
            myRef?.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    addNewsToLIst(dataSnapshot)
                    mAdapter?.refreshAdapter(listOfNews as MutableList<News>)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                }
            })


        }

    fun addNewsToLIst(dataSnapshot: DataSnapshot) {
        for (child in dataSnapshot.children) {
            val article = child.getValue(News::class.java)
            if (article != null) {
                listOfNews.add(article)
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
