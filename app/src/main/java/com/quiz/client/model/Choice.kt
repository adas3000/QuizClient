package com.quiz.client.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Choice {

    @SerializedName("id")
    @Expose
    val id:Long


    @SerializedName("value")
    @Expose
    val value:String

    constructor(id: Long, value: String) {
        this.id = id
        this.value = value
    }
}