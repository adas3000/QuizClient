package com.quiz.client.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Question {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("value")
    @Expose
    var value: String? = null
    @SerializedName("choices")
    @Expose
    var choices: List<Choice>? = null
    @SerializedName("answer")
    @Expose
    var answer: Answer? = null
    @SerializedName("category")
    @Expose
    var category: String? = null




}