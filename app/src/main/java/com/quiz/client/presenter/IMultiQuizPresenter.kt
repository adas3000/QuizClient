package com.quiz.client.presenter



interface IMultiQuizPresenter {

    fun onupdateDeviceScoreInGame(game_code:String,serial:String,howmany:String)

    fun onUpdateDeviceFinishedAnswering(game_code:String,serial:String)

    fun onCheckAllDevicesAnswered(game_code:String)

    fun onFindScoreByUUID(game_code:String)




}