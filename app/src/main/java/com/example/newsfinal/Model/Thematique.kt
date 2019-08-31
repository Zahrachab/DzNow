package com.example.newsfinal.Model

import com.example.newsfinal.Interface.Checkable
import com.google.gson.annotations.SerializedName

class Thematique  (val id : String = "",
                   override var preference : Int = 0,
                   override var checked: Boolean = false,
                   override var designation: String) : Checkable {
}