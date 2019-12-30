package com.quiz.client.presenter

import android.bluetooth.BluetoothAdapter
import android.content.Context

interface IVSOpponentPresenter {
    fun onBluetooth(enableBtnIntentRequestCode:Int,bluetoothAdapter: BluetoothAdapter?)
    fun onQueue(context: Context)
}