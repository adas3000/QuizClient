package com.quiz.client

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
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

        val builder:AlertDialog.Builder = AlertDialog.Builder(this)
            .setMessage("Are you sure?")
            .setPositiveButton("Yes",{DialogInterface,i->
                finishAffinity()
            })
            .setNegativeButton("No",{DialogInterface,i->
                DialogInterface.cancel()
            })
            .setCancelable(false)


        builder.show()
    }

    override fun onOptions() {

    }

    override fun onPlay() {
        startActivity(Intent(this,PlayKindActivity::class.java))
        finish()
    }
}
