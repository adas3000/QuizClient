package com.quiz.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.quiz.client.presenter.IMMenuPresenter
import com.quiz.client.presenter.MMenuPresenter
import com.quiz.client.view.IMMenuView
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() , IMMenuView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)



        MainActivity_textView_Play.setOnClickListener {
            onPlay()
        }
        MainActivity_textView_Options.setOnClickListener {
            onOptions()
        }
        MainActivity_textView_Quit.setOnClickListener {
            onExit()
        }
    }

    override fun onExit() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onOptions() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPlay() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
