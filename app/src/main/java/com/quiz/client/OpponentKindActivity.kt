package com.quiz.client

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quiz.client.presenter.IVSOpponentPresenter
import com.quiz.client.presenter.VSOpponentPresenter
import com.quiz.client.view.IOpponentKindView
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_opponent_kind.*

class OpponentKindActivity : AppCompatActivity(), IOpponentKindView {


    val enableBtnIntentRequestCode = 1

    lateinit var vsOpponentPresenter: IVSOpponentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opponent_kind)

        vsOpponentPresenter = VSOpponentPresenter(this)

        OpponentKindActivity_textView_bluetooth.setOnClickListener {
            vsOpponentPresenter.onBluetooth(enableBtnIntentRequestCode, BluetoothAdapter.getDefaultAdapter())
        }
        OpponentKindActivity_textView_queue.setOnClickListener {
            vsOpponentPresenter.onQueue(this)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == enableBtnIntentRequestCode) {

            if (resultCode == Activity.RESULT_OK) {
                makeBluetoothConn()
            }

        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, PlayKindActivity::class.java))
        finish()
    }

    override fun onResult(msg: String) {
        Toasty.normal(this, msg, Toasty.LENGTH_SHORT).show()
    }

    override fun onError(msg: String) {
        Toasty.error(this, msg, Toasty.LENGTH_SHORT).show()
    }

    override fun askForBluetooth(requestCode: Int) {
        val enableBtnIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        startActivityForResult(enableBtnIntent, requestCode)
    }

    override fun makeBluetoothConn() {

    }

    override fun makeQueueConn() {


    }
}
