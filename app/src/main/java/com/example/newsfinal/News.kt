package com.example.newsfinal

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
 class News{



     @PrimaryKey(autoGenerate = true)
     var id: Int = 0

     @ColumnInfo(name = "title")
var title: String = ""

@ColumnInfo(name = "description")
var description: String = ""


@ColumnInfo(name = "image")
var image: String =""

@ColumnInfo(name = "auteur")
var author: String = ""


@ColumnInfo(name = "categorie")
var categorie: String = ""


@ColumnInfo(name = "date")
var date: String = ""


}