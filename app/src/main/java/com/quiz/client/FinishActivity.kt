package com.quiz.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val intent:Intent = intent


        val score = intent.getStringExtra("score")
        val all = intent.getStringExtra("all")

        textViewscore.setText("Score:"+score+"/"+all)

        textView_No.setOnClickListener {
            exitApp()
        }
        textView_Yes.setOnClickListener {
            fromBegin()
        }



    }

    fun fromBegin(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    fun exitApp(){
        this.finishAffinity()
    }

}
