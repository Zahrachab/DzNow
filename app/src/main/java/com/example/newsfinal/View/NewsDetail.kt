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
import com.like.LikeButton
import com.like.OnLikeListener
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

        likeButton.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton) {

            }

            override fun unLiked(likeButton: LikeButton) {

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

            val chooserIntent = Intent.createChooser(targetedShareIntents.removeAt(0), "Select app to share")
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toTypedArray<Parcelable>())
            startActivity(chooserIntent)
        }
    }

    companion object {
        var article : News? = null
    }

}

