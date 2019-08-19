package com.example.newsfinal

import android.arch.persistence.room.*


@Dao
interface NewsDao {

    @Query("SELECT * FROM News")
    fun getAll(): List<News>
    @Insert
    fun insert(p: News)

    @Delete
    fun delete(p: News)








    }