package com.example.newsfinal.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity

class News(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: Int? = null,
    var title: String  = "",
    var description: String = "",
    var date: String = "",
    var image: String ="",
    var categorie: String = "",
    var author: String = "",
    var url: String = "",
    var site: String = "",
    var thematique: String = ""
    ) : Serializable {

}