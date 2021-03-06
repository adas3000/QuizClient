package com.quiz.client.presenter

import android.bluetooth.BluetoothAdapter
import android.content.Context
import com.quiz.client.model.VSOpponentModel.IVSOpponentBluetooth
import com.quiz.client.model.VSOpponentModel.IVSOpponentQueue
import com.quiz.client.model.VSOpponentModel.VSOpponentBluetooth
import com.quiz.client.model.VSOpponentModel.VSOpponentQueue
import com.quiz.client.view.IOpponentKindView

class VSOpponentPresenter : IVSOpponentPresenter {

    val iOpponentKindView:IOpponentKindView

    constructor(iOpponentKindView: IOpponentKindView){
        this.iOpponentKindView = iOpponentKindView
    }

    override fun onCreateRoom() {
        iOpponentKindView.onRoom()
    }

    override fun onQueue(context: Context) {

        val queueConn:IVSOpponentQueue = VSOpponentQueue()
        if(!queueConn.hasInternetConn(context)){
            iOpponentKindView.onError("No internet connection")
            return
        }
        println(queueConn.getToken())
        iOpponentKindView.makeQueueConn()
    }
}