package com.example.newsfinal

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager
import android.content.Intent
import android.widget.Toast



import kotlinx.android.synthetic.main.fragment_list_news.*

class ListNews : Fragment() {

    private var listOfNews : List<News>? = null

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
                layoutManager = LinearLayoutManager(activity)
                    adapter = ListNewsAdapter(listOfNews, { partItem : News  -> partItemClicked(partItem) })

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
            fun newInstance(list: List<News>?) :
                ListNews {
                val fragment = ListNews()
                fragment.listOfNews = list
                return fragment
            }
        }

}
