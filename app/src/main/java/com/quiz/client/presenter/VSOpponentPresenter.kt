package com.quiz.client.presenter

import android.bluetooth.BluetoothAdapter
import com.quiz.client.view.IOpponentKindView

class VSOpponentPresenter : IVSOpponentPresenter {

    val iOpponentKindView:IOpponentKindView

    constructor(iOpponentKindView: IOpponentKindView){
        this.iOpponentKindView = iOpponentKindView
    }

    override fun onBluetooth(enableBtnIntentRequestCode: Int, bluetoothAdapter: BluetoothAdapter?) {

    }

    override fun onQueue() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}