package com.quiz.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quiz.client.view.IPlayMenuView
import com.quiz.client.util.hasInternetConnection
import es.dmoral.toasty.Toasty
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

        if(!hasInternetConnection(this)){
            Toasty.error(this,"No internet connection",Toasty.LENGTH_SHORT).show()
            return
        }

        startActivity(Intent(this, PlayActivity::class.java))
        finish()
    }

    override fun onPlayVsOpponent() {

        if(!hasInternetConnection(this)){
            Toasty.error(this,"No internet connection",Toasty.LENGTH_SHORT).show()
            return
        }
        startActivity(Intent(this,OpponentKindActivity::class.java))
        finish()
    }


    override fun onBackPressed() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

}
