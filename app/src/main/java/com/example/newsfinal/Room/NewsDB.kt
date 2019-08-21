package com.example.newsfinal.Room

import android.arch.persistence.room.*
import android.content.Context
import com.example.newsfinal.News


@Database(entities = [News::class], version = 1)
abstract class NewsDB : RoomDatabase() {
    abstract fun articleDao(): NewsDao

    companion object {
        private var instance: NewsDB? = null

        fun getInstance(context: Context): NewsDB? {
            if (instance == null) {

                instance = Room.databaseBuilder(context.applicationContext,
                    NewsDB::class.java, "NewsDb").allowMainThreadQueries().build()

            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}
