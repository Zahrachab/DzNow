package com.example.newsfinal.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable



data class News(
    @SerializedName("id") val id: Int? = null,
    val title: String,
    val description: String,
    val date: String,
    val image: String,
    val categorie: String,
    val author: String,
    val url: String,
    val site: String,
    val thematique: String
    ) : Serializable {

}