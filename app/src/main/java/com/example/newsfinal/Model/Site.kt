package com.example.newsfinal.Model

import com.example.newsfinal.Interface.Checkable
import com.google.gson.annotations.SerializedName

class Site  (
    val id: String = "",
    @SerializedName("nom") override var designation: String = "", //nom du site
    val url: String = "",
    override var preference: Int = 0 ,
    override var checked: Boolean = false) : Checkable {

}