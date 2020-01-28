package com.quiz.client.view

interface IOpponentKindView {
    fun onResult(msg:String)
    fun onError(msg:String)
    fun onRoom()
    fun makeQueueConn()
}