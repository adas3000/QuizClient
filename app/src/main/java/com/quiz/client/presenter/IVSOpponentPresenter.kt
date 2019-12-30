package com.quiz.client.presenter

import android.bluetooth.BluetoothAdapter

interface IVSOpponentPresenter {
    fun onBluetooth(enableBtnIntentRequestCode:Int,bluetoothAdapter: BluetoothAdapter?)
    fun onQueue()
}