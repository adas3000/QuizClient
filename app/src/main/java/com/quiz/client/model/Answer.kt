package com.quiz.client.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Answer {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("correct")
    @Expose
    var correct: Choice? = null

}