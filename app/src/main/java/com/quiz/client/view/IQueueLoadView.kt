package com.quiz.client.view

interface IQueueLoadView {
    fun onError(msg:String)
    fun onSuccess(code:String)
}