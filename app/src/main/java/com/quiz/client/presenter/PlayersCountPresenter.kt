package com.quiz.client.presenter

class PlayersCountPresenter : IPlayersCountPresenter {

    val category:String
    val questionCount:Int

    constructor(category:String,questionCount:Int){
        this.category = category
        this.questionCount = questionCount
    }


    override fun onCountSelect(value: Int) {

    }

}