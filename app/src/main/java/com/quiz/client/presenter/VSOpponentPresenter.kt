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

    override fun onBluetooth(enableBtnIntentRequestCode: Int, bluetoothAdapter: BluetoothAdapter?) {

        val bluetooth:IVSOpponentBluetooth = VSOpponentBluetooth(enableBtnIntentRequestCode,bluetoothAdapter)

        if(bluetooth.deviceHasNoBluetooth()){
            iOpponentKindView.onError("Device has no bluetooth connection")
            return
        }

        if(bluetoothAdapter?.isEnabled==false)
            iOpponentKindView.askForBluetooth(enableBtnIntentRequestCode)
        else
            iOpponentKindView.makeBluetoothConn()
    }

    override fun onQueue(context: Context) {

        val queueConn:IVSOpponentQueue = VSOpponentQueue()
        if(!queueConn.hasInternetConn(context)){
            iOpponentKindView.onError("No internet connection")
            return
        }
        println(queueConn.getToken(context))
        iOpponentKindView.makeQueueConn()
    }
}