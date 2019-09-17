package com.example.newsfinal.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Build
import android.speech.tts.TextToSpeech
import android.support.annotation.RequiresApi
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_audio_convert_text.*
import java.util.*

class audioConvertText : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var buttonSpeak: Button? = null
    private var edittext_input: EditText? = null
    private var input: String?=null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.newsfinal.R.layout.activity_audio_convert_text)


        //input = intent.getStringExtra("text")
        input="Il faut afficher de vrais articles d’actualité (les plus récents) provenant des sites algériens (français\n" +
                "et arabe)\n" +
                "- Ajouter la fonction sauvegarder article qui sauvegarde l’article (texte intégral) pour une lecture\n" +
                "ultérieure en mode offline\n" +
                "- Ajouter la fonction sites préférés et thèmes préférés. L’application scanne les sites préférés et\n" +
                "affiche une notification dès qu’un nouvel article concernant une des thématiques préférées est\n" +
                "publié."

        text.text=input
        buttonSpeak = this.button_speak1
        buttonSpeak!!.isEnabled = false;
        tts = TextToSpeech(this, this)

        buttonSpeak!!.setOnClickListener { speakOut() }
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.FRANCE)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            } else {
                buttonSpeak!!.isEnabled = true
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun speakOut() {
        val text = input
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    public override fun onDestroy() {
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}
