package com.quiz.client.view

interface IOpponentKindView {
    fun onResult(msg:String)
    fun onError(msg:String)
    fun askForBluetooth(requestCode: Int)
    fun makeBluetoothConn()
    fun makeQueueConn()
}