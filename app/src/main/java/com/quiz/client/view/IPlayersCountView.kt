package com.quiz.client.view

interface IPlayersCountView {
    fun onSuccess(gameUUID:String)
    fun onError(msg:String)
}