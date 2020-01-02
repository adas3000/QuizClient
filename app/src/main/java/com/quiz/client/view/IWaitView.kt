package com.quiz.client.view

interface IWaitView {
    fun onSuccess(code:String)
    fun onError(msg:String)
    fun onContinue(code:String)
}