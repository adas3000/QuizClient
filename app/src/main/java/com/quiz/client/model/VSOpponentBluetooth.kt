package com.quiz.client.model

import android.bluetooth.BluetoothAdapter
import com.quiz.client.view.IOpponentKindView

class VSOpponentBluetooth  {

    val enableBtnIntentRequestCode:Int
    val bluetoothAdapter:BluetoothAdapter?

    constructor(enableBtnIntentRequestCode:Int,bluetoothAdapter:BluetoothAdapter?){
        this.enableBtnIntentRequestCode = enableBtnIntentRequestCode
        this.bluetoothAdapter = bluetoothAdapter
    }


}