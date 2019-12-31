package com.quiz.client.presenter

import com.quiz.client.service.OpponentApiService

interface IQueueLoadPresenter {
    fun onGoToQueue()
    fun onSearchOpponent()
    fun onDropFromQueue()
}