package com.example.newsfinal.View

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.Telephony
import android.support.annotation.RequiresApi
import com.example.newsfinal.Model.News
import com.example.newsfinal.R

import android.util.Log
import com.like.LikeButton
import com.like.OnLikeListener
import com.example.newsfinal.Room.*
import kotlinx.android.synthetic.main.activity_news_detail.*


class NewsDetail : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        val defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this)

        btn_share.setOnClickListener {
            share("gmail", defaultSmsPackageName)}
        bindData()



            var intentThatStartedThisActivity = getIntent()


        val db =NewsDB.getInstance(this)

            val dao = db?.articleDao()

            if (article?.id?.let { dao?.getNewsById(it) } != null ) {


                likeButton.isLiked=true
            }


            var art = News()
            var act=this
            art.id= article?.id
            art.title = article!!.title
            art.description =   article!!.description
            art.image = article!!.image
            art.author = article!!.author
            art.categorie = article!!.categorie
            art.date = article!!.date
            art.url= article!!.url




            likeButton.setOnLikeListener(object : OnLikeListener {
                override fun liked(likeButton: LikeButton) {

                    dao?.saveNews(art)

                    AppTools.showToast(act, "Article archivé")

                    dao?.getNews()?.forEach()
                    {
                        Log.i("Fetch Records", "Id:  : ${it.id}")
                        Log.i("Fetch Records", "Name:  : ${it.author}")

                    }

                }

                override fun unLiked(likeButton: LikeButton) {
                    dao?.deleteNews(art)
                    AppTools.showToast(act, "Article supprimié")
                    dao?.getNews()?.forEach()
                    {
                        Log.i("Fetch Records", "Id:  : ${it.id}")
                        Log.i("Fetch Records", "Name:  : ${it.author}")

                    }

                }
            })


            //fetch Records





    }

    fun bindData() {
        categorie_news.text = article!!.categorie
        title_article.text = article!!.title
        date.text = article!!.date
        categorie_article.text = article!!.categorie
        auteur.text = article!!.author
        txt_descreption.text = article!!.description
    }

    private fun share(nameApp: String, nameApp2: String) {
        val targetedShareIntents = ArrayList<Intent>()
        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/jpeg"
        val resInfo = packageManager.queryIntentActivities(share, 0)
        if (!resInfo.isEmpty()) {
            for (info in resInfo) {
                val targetedShare = Intent(Intent.ACTION_SEND)
                targetedShare.type = "image/jpeg" // put here your mime type

                if (info.activityInfo.packageName.toLowerCase().contains(nameApp) || info.activityInfo.name.toLowerCase().contains(
                        nameApp) || info.activityInfo.packageName.toLowerCase().contains(nameApp2) || info.activityInfo.name.toLowerCase().contains(
                        nameApp2)
                )  {
                    targetedShare.putExtra(Intent.EXTRA_TEXT, article!!.url)

                    targetedShare.setPackage(info.activityInfo.packageName)
                    targetedShareIntents.add(targetedShare)
                }


            }

            val chooserIntent = Intent.createChooser(targetedShareIntents.removeAt(0), "Sélectionner une application")
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toTypedArray<Parcelable>())
            startActivity(chooserIntent)
        }
    }

    companion object {
        var article : News? = null
    }

}

