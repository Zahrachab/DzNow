package com.example.newsfinal

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey



@Entity
 class News{



     @PrimaryKey(autoGenerate = true)
     var id: Int = 0

     @ColumnInfo(name = "title")
var title: String? = null

@ColumnInfo(name = "description")
var description: String? = null


@ColumnInfo(name = "image")
var image: String? = null

@ColumnInfo(name = "auteur")
var author: String? = null


@ColumnInfo(name = "categorie")
var categorie: String? = null


@ColumnInfo(name = "date")
var date: String? = null

}