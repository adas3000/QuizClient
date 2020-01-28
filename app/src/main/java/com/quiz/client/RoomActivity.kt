package com.quiz.client

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.quiz.client.adapter.RecyclerViewPlayersCountAdapter
import com.quiz.client.presenter.IPlayersCountPresenter
import com.quiz.client.presenter.PlayersCountPresenter
import kotlinx.android.synthetic.main.activity_room.*
import java.util.*


class RoomActivity : AppCompatActivity() {

    lateinit var category: String
    var questionCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        category = intent.getStringExtra("category")
        questionCount = intent.getIntExtra("count",5)

        val playersCountPresenter:IPlayersCountPresenter = PlayersCountPresenter()


        val rv = rv_players_count
        rv.layoutManager = GridLayoutManager(this,2)
        rv.setHasFixedSize(false)
        rv.adapter = RecyclerViewPlayersCountAdapter(sortedSetOf(2,4,3,5),playersCountPresenter)


    }

    override fun onBackPressed() {
        startActivity(Intent(this,SecondActivity::class.java))
        finish()
    }

}
