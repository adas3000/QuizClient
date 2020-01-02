package com.quiz.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quiz.client.component.AppComponent
import com.quiz.client.component.DaggerAppComponent
import com.quiz.client.presenter.IWaitPresenter
import com.quiz.client.presenter.WaitPresenter
import com.quiz.client.service.GameApiService
import com.quiz.client.view.IWaitView
import retrofit2.Retrofit
import javax.inject.Inject

class WaitActivity : AppCompatActivity() , IWaitView {


    @Inject
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wait)

        val game_code:String? = this.intent.getStringExtra("game_code")

        if(game_code==null){
            throw NullPointerException("game_code is null")
        }

        val appComponent: AppComponent = DaggerAppComponent.builder().build()
        retrofit = appComponent.provideRetrofit()

        val gameApiService:GameApiService = retrofit.create(GameApiService::class.java)
        val waitPresenter:IWaitPresenter = WaitPresenter(this,gameApiService)
        waitPresenter.doCheckAllConnected(game_code)
    }

    override fun onSuccess(code: String) {

    }

    override fun onError(msg: String) {

    }

    override fun onContinue(code: String) {

    }
}
