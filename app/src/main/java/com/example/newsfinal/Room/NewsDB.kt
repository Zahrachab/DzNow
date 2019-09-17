package com.example.newsfinal.Room

import android.arch.persistence.room.*
import android.content.Context
import com.example.newsfinal.Model.News



@Database(entities = [News::class], version = 1)
abstract class NewsDB : RoomDatabase() {
    abstract fun articleDao(): NewsDao

    companion object {
        private var instance: NewsDB? = null

        fun getInstance(context: Context): NewsDB? {
            if (instance == null) {

                instance = Room.databaseBuilder(context.getApplicationContext(),
                    NewsDB::class.java, "NewsDb").allowMainThreadQueries().build()

            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}
