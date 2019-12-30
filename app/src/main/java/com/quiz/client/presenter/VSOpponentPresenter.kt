package com.quiz.client.presenter

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult
import com.quiz.client.model.VSOpponentModel.IVSOpponentBluetooth
import com.quiz.client.model.VSOpponentModel.VSOpponentBluetooth
import com.quiz.client.view.IOpponentKindView

class VSOpponentPresenter : IVSOpponentPresenter {

    val iOpponentKindView:IOpponentKindView

    constructor(iOpponentKindView: IOpponentKindView){
        this.iOpponentKindView = iOpponentKindView
    }

    override fun onBluetooth(enableBtnIntentRequestCode: Int, bluetoothAdapter: BluetoothAdapter?) {

        val bluetooth:IVSOpponentBluetooth = VSOpponentBluetooth(enableBtnIntentRequestCode,bluetoothAdapter)

        if(!bluetooth.deviceHasBluetooth()){
            iOpponentKindView.onError("Device has no bluetooth connection")
            return
        }

        if(bluetoothAdapter?.isEnabled==false){

        }
    }

    override fun onQueue() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}