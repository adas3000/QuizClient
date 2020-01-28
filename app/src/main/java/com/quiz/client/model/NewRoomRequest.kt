package com.quiz.client.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewRoomRequest {

    @SerializedName("serial")
    @Expose
    var serial: String? = null

    @SerializedName("category")
    @Expose
    var category: String? = null

    @SerializedName("questionCount")
    @Expose
    var questionCount: Int = 0

    @SerializedName("playerCount")
    @Expose
    var playerCount: Int = 0
}