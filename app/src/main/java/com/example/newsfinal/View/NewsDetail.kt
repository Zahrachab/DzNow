package com.example.newsfinal.View

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.Telephony
import android.speech.tts.TextToSpeech
import android.support.annotation.RequiresApi
import com.example.newsfinal.Model.Article
import com.example.newsfinal.R
import android.widget.Toast
import com.bumptech.glide.Glide
import com.like.LikeButton
import com.like.OnLikeListener
import com.example.newsfinal.Room.*
import com.example.newsfinal.Services.GoogeInfosService
import kotlinx.android.synthetic.main.activity_news_detail.*
import java.util.*
import kotlin.collections.ArrayList

import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_audio_convert_text.*
import java.util.*



    /**
     * Activity pour l'affichage d'un article
     */
    class NewsDetail : AppCompatActivity(), TextToSpeech.OnInitListener {
        override fun onInit(status: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        @RequiresApi(Build.VERSION_CODES.KITKAT)

        //context
        var context: Context? = null
        //Instance de la base de données locale
        private var db: NewsDB? = null
        //DAO de l'article
        private var dao: ArticleDao? = null

        //service SignetService
        private var service: GoogeInfosService? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_news_detail)
            context = this

            //initialiser db et dao
            db = NewsDB.getInstance(this)
            dao = db?.articleDao()

            //service SignetsService pour synchroniser avec le backend les articles archivés pour un compte google
            service = GoogeInfosService(this)

            initialiseView()
        }


        fun initialiseView() {

            //vérifier si l'article existe en local
            if (article?.id?.let { dao?.getNewsById(it) } != null) {
                //initialiser le button like
                likeButton.isLiked = true
            }

            //click listener pour le boutton like
            likeButton.setOnLikeListener(object : OnLikeListener {

                //button like
                override fun liked(likeButton: LikeButton) {

                    //envoyer au service web pour sauvegarde
                    service?.saveSignetCompte(article?.id.toString())

                    //sauvegarder dans sqlite
                    article?.let { dao?.saveNews(it) }
                    Toast.makeText(context, "article archivé", Toast.LENGTH_LONG).show()

                }

                //button unlike
                override fun unLiked(likeButton: LikeButton) {

                    //envoyer au service web pour supprimer
                    service?.deleteSignetCompte(article?.id.toString())

                    //supprimer du sqlite
                    article?.let { dao?.deleteNews(it) }
                    Toast.makeText(context, "article supprimé de l'archive", Toast.LENGTH_LONG).show()
                }
            })

            //click listener pour le boutton partager
            btn_share.setOnClickListener {
                val defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this)
                share("gmail", defaultSmsPackageName)
            }
            bindData()

            //fetch Records
            fetchRecords()


        }


        fun fetchRecords() {

            btnAddEvt.setOnClickListener {

                var input = article!!.description

                val intent = Intent(this, addEvtCal::class.java)
                intent.putExtra("text", "$input")

                startActivity(intent)
            }
        }


        fun bindData() {
            categorie_news.text = article?.categorie
            title_article.text = article?.title
            date.text = article?.date
            categorie_article.text = article?.categorie
            auteur.text = article?.author
            txt_descreption.text = article?.description
            site_article.text = article?.site
        }


        //fonctionnalité de partage
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
                            nameApp
                        ) || info.activityInfo.packageName.toLowerCase().contains(nameApp2) || info.activityInfo.name.toLowerCase().contains(
                            nameApp2
                        )
                    ) {
                        targetedShare.putExtra(Intent.EXTRA_TEXT, article!!.url)

                        targetedShare.setPackage(info.activityInfo.packageName)
                        targetedShareIntents.add(targetedShare)
                    }


                }

                val chooserIntent =
                    Intent.createChooser(targetedShareIntents.removeAt(0), "Sélectionner une application")
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toTypedArray<Parcelable>())
                startActivity(chooserIntent)
            }
        }

        companion object {
            var article: Article? = null
        }
    }



