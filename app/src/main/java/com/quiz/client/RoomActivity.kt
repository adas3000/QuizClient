package com.quiz.client

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent


class RoomActivity : AppCompatActivity() {

    lateinit var category: String
    var questionCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        category = intent.getStringExtra("category")
        questionCount = intent.getIntExtra("count",5)





    }

    override fun onBackPressed() {
        startActivity(Intent(this,SecondActivity::class.java))
        finish()
    }

}
