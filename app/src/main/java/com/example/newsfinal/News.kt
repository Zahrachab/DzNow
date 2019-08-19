package com.example.newsfinal

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable


@Entity

data class News(@PrimaryKey(autoGenerate = true)
                val id: Int? = null,
                val title: String, val description: String, val date: String, val image: String, val categorie: String, val author: String) : Serializable {

}