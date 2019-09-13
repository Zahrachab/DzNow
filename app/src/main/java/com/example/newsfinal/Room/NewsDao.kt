package com.example.newsfinal.Room


import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.newsfinal.News

@Dao
interface NewsDao {

    @Insert
    fun saveNews(art: News)



    @Query("SELECT * FROM News")
    fun getNews (): List<News>

    @Query("SELECT * FROM News WHERE id = :arg0")
    fun getNewsById(id: Int): News

    @Delete
    fun deleteNews(art:  News)

}