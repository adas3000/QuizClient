package com.quiz.client

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.quiz.client.component.AppComponent
import com.quiz.client.component.DaggerAppComponent
import com.quiz.client.fragment.QuestionFragment
import com.quiz.client.service.GameApiService
import com.quiz.client.view.IMultiQuizView
import retrofit2.Retrofit
import java.lang.NullPointerException
import javax.inject.Inject

class MultiQuizActivity : AppCompatActivity() , IMultiQuizView {

    @Inject
    lateinit var retrofit:Retrofit

    val points:Int = R.string.points_for_good_question_text

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

        val ft:FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.multi_quiz_placeholder,QuestionFragment())
        ft.commit()
    }


    override fun onBackPressed() {
        //todo alert
    }

    override fun onNextQuestion(correct: Boolean, time_remaining: Int) {

    }

}
