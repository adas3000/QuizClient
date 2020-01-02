package com.quiz.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quiz.client.component.AppComponent
import com.quiz.client.component.DaggerAppComponent
import com.quiz.client.presenter.IWaitPresenter
import com.quiz.client.presenter.WaitPresenter
import com.quiz.client.service.GameApiService
import com.quiz.client.view.IWaitView
import es.dmoral.toasty.Toasty
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
        val intent = Intent(this,QuizActivity::class.java)
        startActivity(intent)
    }

    override fun onError(msg: String) {
        Toasty.error(this,msg,Toasty.LENGTH_SHORT).show()
    }

    override fun onContinue(code: String,iWaitPresenter: IWaitPresenter) {
        iWaitPresenter.doCheckAllConnected(code)
    }
}
