package com.quiz.client.model.VSOpponentModel

import android.bluetooth.BluetoothAdapter

class VSOpponentBluetooth : IVSOpponentBluetooth  {

    val enableBtnIntentRequestCode:Int
    val bluetoothAdapter:BluetoothAdapter?

    constructor(enableBtnIntentRequestCode:Int,bluetoothAdapter:BluetoothAdapter?){
        this.enableBtnIntentRequestCode = enableBtnIntentRequestCode
        this.bluetoothAdapter = bluetoothAdapter
    }

    override fun deviceHasBluetooth(): Boolean {
        return bluetoothAdapter==null
    }

    override fun getEnableBtnIntentRequestCodee(): Int {
        return enableBtnIntentRequestCode
    }

}