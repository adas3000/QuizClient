package com.quiz.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.google.firebase.FirebaseApp
import com.quiz.client.database.AppDatabase
import com.quiz.client.module.AppModule
import com.quiz.client.presenter.IUserPresenter
import com.quiz.client.presenter.UserPresenter
import com.quiz.client.view.IMMenuView
import com.quiz.client.view.IUserView
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity(), IMMenuView, IUserView {

    lateinit var userPresenter: IUserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        FirebaseApp.initializeApp(this)

        userPresenter = UserPresenter(this, Room.databaseBuilder(this, AppDatabase::class.java, "quizDB01").build())
        userPresenter.checkNickNameSet()

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

        var nick: String = "unknown"

        this.runOnUiThread(Runnable {

            val editText: EditText = EditText(this)
            val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            editText.layoutParams = params

            val alertDialog: AlertDialog = AlertDialog.Builder(this)
                .setTitle("Nick")
                .setMessage("Hello,i've see that is your first time here,write your nickname and start QQuuizing!")
                .setCancelable(false)
                .setPositiveButton("Confirm", { dialog, which ->
                })
                .setView(editText)
                .create()

            alertDialog.show()

            val positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {
                nick = editText.text.toString()

                if (nick.length < 3) {
                    Toasty.info(
                        applicationContext,
                        "Write at least 3 characters",
                        Toasty.LENGTH_SHORT
                    ).show()
                } else {
                    alertDialog.dismiss()
                    AppModule.usernickname = nick
                    userPresenter.saveNickName(nick)
                }

            }


        })

    }

    override fun onError(msg: String) {
        this.runOnUiThread(Runnable {
            Toasty.error(this, msg, Toasty.LENGTH_SHORT).show()
        })
    }

    override fun onNickNameExist(nick:String) {
        AppModule.usernickname = nick
    }

    override fun onExit() {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            .setMessage("Are you sure?")
            .setPositiveButton("Yes", { DialogInterface, i ->
                finishAffinity()
            })
            .setNegativeButton("No", { DialogInterface, i ->
                DialogInterface.cancel()
            })
            .setCancelable(false)


        builder.show()
    }

    override fun onOptions() {

    }

    override fun onPlay() {
        startActivity(Intent(this, PlayKindActivity::class.java))
        finish()
    }
}
