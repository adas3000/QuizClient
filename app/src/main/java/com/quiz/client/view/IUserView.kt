package com.quiz.client.view

interface IUserView {
    fun onBlankNickName()
    fun onError(msg:String)
    fun onNickNameExist()
}