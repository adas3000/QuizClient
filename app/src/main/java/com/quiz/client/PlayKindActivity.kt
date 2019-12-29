package com.quiz.client

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quiz.client.view.IPlayMenuView
import kotlinx.android.synthetic.main.activity_play_kind.*

class PlayKindActivity : AppCompatActivity(), IPlayMenuView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_kind)

        PlayKindActivity_textView_Alone.setOnClickListener {
            onPlayAlone()
        }
        PlayKindActivity_textView_VsOpponent.setOnClickListener {
            onPlayVsOpponent()
        }


    }

    override fun onPlayAlone() {
        startActivity(Intent(this, PlayActivity::class.java))
    }

    override fun onPlayVsOpponent() {

    }




}
