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

        OpponentKindActivity_textView_createRoom.setOnClickListener {
            vsOpponentPresenter.onCreateRoom()
        }
        OpponentKindActivity_textView_queue.setOnClickListener {
            vsOpponentPresenter.onQueue(this)
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

    override fun onRoom() {
        startActivity(Intent(this,RoomActivity::class.java))
        finish()
    }

    override fun makeQueueConn() {
        startActivity(Intent(this,QueueLoadActivity::class.java))
        finish()
    }
}
