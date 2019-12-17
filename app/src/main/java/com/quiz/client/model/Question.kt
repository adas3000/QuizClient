package com.quiz.client.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Question {

    @SerializedName("id")
    @Expose
    val id:Long

    @SerializedName("content")
    @Expose
    val content:String

    @SerializedName("choices")
    @Expose
    var choices = HashSet<Choice>()


    @SerializedName("answer")
    @Expose
    val answer:Answer


    @SerializedName("category")
    @Expose
    val category:String

    constructor(id: Long, content: String, answer: Answer, category: String) {
        this.id = id
        this.content = content
        this.answer = answer
        this.category = category
    }

    constructor(){
        this.id = 0
        this.content=""
        answer = Answer()
        category=""
    }

}