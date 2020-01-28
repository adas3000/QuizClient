package com.quiz.client

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import com.quiz.client.adapter.RecyclerViewPlayersCountAdapter
import com.quiz.client.component.AppComponent
import com.quiz.client.component.DaggerAppComponent
import com.quiz.client.presenter.IPlayersCountPresenter
import com.quiz.client.presenter.PlayersCountPresenter
import com.quiz.client.service.OpponentApiService
import com.quiz.client.view.IPlayersCountView
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_room.*
import retrofit2.Retrofit
import javax.inject.Inject

class RoomActivity : AppCompatActivity(),IPlayersCountView {

    @Inject
    lateinit var retrofit: Retrofit

    lateinit var category: String
    var questionCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        val appComponent: AppComponent = DaggerAppComponent.builder().build()
        retrofit = appComponent.provideRetrofit()

        category = intent.getStringExtra("category")
        questionCount = intent.getIntExtra("count",5)

        val playersCountPresenter:IPlayersCountPresenter = PlayersCountPresenter(this,retrofit.create(OpponentApiService::class.java))


        val rv = rv_players_count
        rv.layoutManager = GridLayoutManager(this,2)
        rv.setHasFixedSize(false)
        rv.adapter = RecyclerViewPlayersCountAdapter(sortedSetOf(2,4,3,5),playersCountPresenter,category,questionCount)
    }

    override fun onBackPressed() {
        startActivity(Intent(this,SecondActivity::class.java))
        finish()
    }

    override fun onError(msg: String) {
        Toasty.error(this,msg,Toasty.LENGTH_SHORT).show()
    }

    override fun onSuccess(gameUUID: String) {
        Toasty.success(this,gameUUID,Toasty.LENGTH_LONG).show()
    }

}
