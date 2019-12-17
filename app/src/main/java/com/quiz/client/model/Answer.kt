package com.quiz.client.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Answer {

    @SerializedName("id")
    @Expose
    val id: Long

    @SerializedName("correct")
    @Expose
    val correct: Choice


    constructor(id: Long, correct: Choice) {
        this.id = id
        this.correct = correct
    }
    constructor(){
        id = 0
        correct = Choice(0,"")
    }
}