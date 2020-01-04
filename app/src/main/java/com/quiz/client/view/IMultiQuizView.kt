package com.quiz.client.view

import com.quiz.client.model.Score


interface IMultiQuizView {
    fun onError(msg:String)
    fun onupdateDeviceFinishedAnsweringToQuestionSuccess()
    fun oncheckAllDevicesAnsweredSuccess()
    fun onfindScoresByUUID(scores:List<Score>)
    fun onScoreDeviceUpdateSuccess()
    fun onWaitForOthers()
    fun onPlayerReadySentSuccess()
    fun onCheckNextQuestionAv()
    fun onWaitForNextQuestionAv()
}