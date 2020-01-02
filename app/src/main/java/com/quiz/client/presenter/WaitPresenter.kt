package com.quiz.client.presenter

import com.quiz.client.service.GameApiService
import com.quiz.client.view.IWaitView

class WaitPresenter : IWaitPresenter {


    val iWaitView:IWaitView
    val gameApiService:GameApiService

    constructor(iWaitView:IWaitView,gameApiService:GameApiService){
        this.iWaitView = iWaitView
        this.gameApiService = gameApiService
    }

    override fun doCheckAllConnected(uuid: String) {

    }


}