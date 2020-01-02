package com.quiz.client

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.quiz.client.component.AppComponent
import com.quiz.client.component.DaggerAppComponent
import com.quiz.client.fragment.QuestionFragment
import com.quiz.client.service.GameApiService
import com.quiz.client.util.QuestionListKeeper
import com.quiz.client.view.IMultiQuizView
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_quiz.*
import retrofit2.Retrofit
import java.lang.NullPointerException
import javax.inject.Inject

class MultiQuizActivity : AppCompatActivity(), IMultiQuizView {

    @Inject
    lateinit var retrofit: Retrofit

    lateinit var gameApiService: GameApiService

    var allQuestionCount:Int=0
    var correctCount:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_quiz)

        val game_code: String? = this.intent.getStringExtra("game_code")

        if (game_code == null) {
            throw NullPointerException("game_code is null")
        }

        val appComponent: AppComponent = DaggerAppComponent.builder().build()
        retrofit = appComponent.provideRetrofit()

        gameApiService = retrofit.create(GameApiService::class.java)

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.multi_quiz_placeholder, QuestionFragment.newInstance(0,this))
        ft.commit()
    }


    override fun onBackPressed() {
        //todo alert
    }

    override fun onNextQuestion(correct: Boolean, time_remaining: Int) {

        var color:String = "#82DD55" // success color

        if(correct){
            correctCount++
            Toasty.success(this,"Good", Toasty.LENGTH_SHORT).show()
        }

        else {
            Toasty.error(this,"Wrong", Toasty.LENGTH_SHORT).show()
            color = "#E23636" // error color
        }
        rv_top.findViewHolderForAdapterPosition(allQuestionCount)?.itemView?.findViewById<TextView>(R.id.textView_square)?.setBackgroundColor(
            Color.parseColor(color))

        allQuestionCount++

        textView_question_count.setText(correctCount.toString()+"/"+allQuestionCount.toString())

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.multi_quiz_placeholder, QuestionFragment.newInstance(0,this))
        ft.commit()

    }

}
