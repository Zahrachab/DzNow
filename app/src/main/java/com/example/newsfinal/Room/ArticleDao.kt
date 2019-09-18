package com.example.newsfinal.Room


import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.newsfinal.Model.Article

@Dao
interface ArticleDao {

    @Insert
    fun saveNews(art: Article)



    @Query("SELECT * FROM Article")
    fun getNews (): List<Article>

    @Query("SELECT * FROM Article WHERE id = :id")
    fun getNewsById(id: Int): Article

    @Delete
    fun deleteNews(art:  Article)

}