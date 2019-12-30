package com.quiz.client

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quiz.client.view.IOpponentKindView
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_opponent_kind.*

class OpponentKindActivity : AppCompatActivity(),IOpponentKindView {


    val enableBtnIntentRequestCode = 1

    var bluetoothEnabled:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opponent_kind)

        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if(bluetoothAdapter!=null)
            bluetoothEnabled = bluetoothAdapter?.isEnabled

        OpponentKindActivity_textView_bluetooth.setOnClickListener {

            if (bluetoothAdapter == null) {
                Toasty.error(this, "Device has no bluetooth connection", Toasty.LENGTH_SHORT).show()
            }

            else {

                if (bluetoothAdapter?.isEnabled == false) {
                    val enableBtnIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    startActivityForResult(enableBtnIntent, enableBtnIntentRequestCode)
                }
                else{
                    //TODO make bluetooth conn
                }

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == enableBtnIntentRequestCode) {

            if (resultCode == Activity.RESULT_OK){
                bluetoothEnabled = true
                //makeBluetooth conn
            }

        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, PlayKindActivity::class.java))
        finish()
    }

    override fun onResult(msg: String) {
        Toasty.normal(this,msg,Toasty.LENGTH_SHORT).show()
    }

    override fun onError(msg: String) {
        Toasty.error(this,msg,Toasty.LENGTH_SHORT).show()
    }
}
