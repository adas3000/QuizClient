package com.quiz.client.presenter



interface IMultiQuizPresenter {

    fun onupdateDeviceScoreInGame(game_code:String,serial:String,howmany:String)

    fun onUpdateDeviceFinishedAnswering(game_code:String,serial:String)

    fun onCheckAllDevicesAnswered(game_code:String)

    fun onFindScoreByUUID(game_code:String)

    fun onPlayerReadySent(game_code: String)

    fun onNewQuestionCheck(game_code: String)

    fun onUpdateDeviceAnswerState(serial:String,value:Boolean)

    fun onUpdateDeviceReadyForNextState(serial:String,value:Boolean)

}