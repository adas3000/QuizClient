package com.quiz.client.presenter

import android.content.Context

interface IVSOpponentPresenter {
    fun onCreateRoom()
    fun onQueue(context: Context)
}