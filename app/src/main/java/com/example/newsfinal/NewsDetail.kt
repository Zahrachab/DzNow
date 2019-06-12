package com.example.newsfinal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

class NewsDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        var intentThatStartedThisActivity = getIntent()

        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            var partId = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT)
        }
    }
}
