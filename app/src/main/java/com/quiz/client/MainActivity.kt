package com.quiz.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import butterknife.ButterKnife

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)
        

    }
}
