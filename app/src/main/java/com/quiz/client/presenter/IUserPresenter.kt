package com.quiz.client.presenter

import com.quiz.client.database.AppDatabase

interface IUserPresenter {
    fun checkNickNameSet()
    fun saveNickName(nick:String)
}