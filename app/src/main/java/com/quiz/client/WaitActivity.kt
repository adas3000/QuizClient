package com.quiz.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class WaitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wait)

        val game_code:String? = this.intent.getStringExtra("game_code")

        if(game_code==null){
            throw NullPointerException("game_code is null")
        }

    }
    


}
