package com.quiz.client

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent


class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
    }

    override fun onBackPressed() {
        startActivity(Intent(this,OpponentKindActivity::class.java))
        finish()
    }

}
