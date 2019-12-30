package com.quiz.client.presenter

import com.quiz.client.service.OpponentApiService

interface IQueueLoadPresenter {
    fun onGoToQueue(opponentApiService: OpponentApiService)
    fun onSearchOpponent(opponentApiService: OpponentApiService)
    fun onDropFromQueue(opponentApiService: OpponentApiService)
}