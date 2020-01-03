package com.quiz.client.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Score {

    @SerializedName("id")
    @Expose
    var id:String?=null

    @SerializedName("score")
    @Expose
    var score:Int?=null
}