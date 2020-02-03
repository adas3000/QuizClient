package com.quiz.client.presenter

import java.io.Serializable


interface IMultiQuizPresenter : Serializable {

    fun onupdateDeviceScoreInGame(game_code:String,serial:String,howmany:String)

    fun onCheckAllDevicesAnswered(game_code:String)

    fun onFindScoreByUUID(game_code:String)

    fun onNewQuestionCheck(game_code: String)

    fun onUpdateDeviceAnswerState(serial:String,value:Boolean)

    fun onUpdateDeviceReadyForNextState(serial:String,value:Boolean)

    fun onUpdateDevice(serial:String,value_answer:Boolean,value_ready:Boolean)

    fun onGameFinished(game_code: String)

}