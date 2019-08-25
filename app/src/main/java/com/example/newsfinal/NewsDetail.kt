package com.example.newsfinal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.os.AsyncTask
import com.like.LikeButton
import com.like.OnLikeListener
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.activity_news_detail.view.*
import com.example.newsfinal.Room.*


class NewsDetail : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        bindData()
        var intentThatStartedThisActivity = getIntent()

        var act = this
        likeButton.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton) {
                val art = News()

                 art.id= article!!.id
                art.title = article!!.title
                 art.description =   article!!.description
                art.image = article!!.image
                art.author = article!!.author
                art.categorie = article!!.categorie
                art.date = article!!.date
                val db =NewsDB.getInstance(act)
                val dao = db?.articleDao()

                dao?.saveNews(art)

                AppTools.showToast(act, "News archived")
            }

            override fun unLiked(likeButton: LikeButton) {
                val art = News()

                art.id= article!!.id
                art.title = article!!.title
                art.description =   article!!.description
                art.image = article!!.image
                art.author = article!!.author
                art.categorie = article!!.categorie
                art.date = article!!.date


                        val db =NewsDB.getInstance(act)
                        val dao = db?.articleDao()

                        dao?.deleteNews(art)

                        AppTools.showToast(act, "News deleted from archive")



            }
        })
    }

    fun bindData() {
        categorie_news.text = article!!.categorie
        title_article.text = article!!.title
        date.text = article!!.date
        categorie_article.text = article!!.categorie
        auteur.text = article!!.author
        txt_descreption.text = article!!.description


    }
    companion object {
        var article : News? = null
    }

}

