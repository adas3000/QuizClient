package com.quiz.client.presenter

import com.quiz.client.model.Score
import com.quiz.client.service.GameApiService
import com.quiz.client.view.IMultiQuizView

class MultiQuizPresenter : IMultiQuizPresenter {

    val iMultiQuizView:IMultiQuizView
    val gameApiService: GameApiService

    constructor(iMultiQuizView: IMultiQuizView,gameApiService: GameApiService) {
        this.iMultiQuizView = iMultiQuizView
        this.gameApiService = gameApiService
    }

    override fun onupdateDeviceScoreInGame(game_code: String, serial: String, howmany: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpdateDeviceFinishedAnswering(game_code: String, serial: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCheckAllDevicesAnswered(game_code: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFindScoreByUUID(game_code: String): List<Score> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}