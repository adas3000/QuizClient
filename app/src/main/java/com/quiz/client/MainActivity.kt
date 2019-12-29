package com.quiz.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quiz.client.view.IMMenuView

class MainActivity : AppCompatActivity() , IMMenuView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    override fun onOptionsChoose(which: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
