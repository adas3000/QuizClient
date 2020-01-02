package com.quiz.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quiz.client.component.AppComponent
import com.quiz.client.component.DaggerAppComponent
import com.quiz.client.service.GameApiService
import retrofit2.Retrofit
import java.lang.NullPointerException
import javax.inject.Inject

class MultiQuizActivity : AppCompatActivity() {

    @Inject
    lateinit var retrofit:Retrofit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_quiz)

        val game_code:String? = this.intent.getStringExtra("game_code")

        if(game_code==null){
            throw NullPointerException("game_code is null")
        }

        val appComponent: AppComponent = DaggerAppComponent.builder().build()
        retrofit = appComponent.provideRetrofit()

        val gameApiService: GameApiService = retrofit.create(GameApiService::class.java)



    }


    override fun onBackPressed() {
        //todo alert
    }

}
