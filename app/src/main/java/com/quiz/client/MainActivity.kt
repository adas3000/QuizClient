package com.quiz.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.google.firebase.FirebaseApp
import com.quiz.client.database.AppDatabase
import com.quiz.client.presenter.IUserPresenter
import com.quiz.client.presenter.UserPresenter
import com.quiz.client.view.IMMenuView
import com.quiz.client.view.IUserView
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() , IMMenuView , IUserView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        FirebaseApp.initializeApp(this)

        UserPresenter(this,Room.databaseBuilder(this,AppDatabase::class.java,"quizDB01").build()).checkNickNameSet()

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

    override fun onBlankNickName() {

        this.runOnUiThread(Runnable {
                
        })

    }

    override fun onError(msg:String) {
        this.runOnUiThread(Runnable {
            Toasty.error(this,msg,Toasty.LENGTH_SHORT).show()
        })
    }

    override fun onNickNameExist() {

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
