package com.quiz.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnTouch
import com.quiz.client.component.AppComponent
import com.quiz.client.component.DaggerAppComponent
import com.quiz.client.model.Question
import com.quiz.client.presenter.HowManyPresenter
import com.quiz.client.service.QuizApiService
import com.quiz.client.view.IHowManyView
import kotlinx.android.synthetic.main.activity_second.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class SecondActivity : AppCompatActivity(), IHowManyView {

    @Inject
    lateinit var retrofit: Retrofit

    lateinit var quizApiService: QuizApiService

    lateinit var howManyPresenter: HowManyPresenter

    lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        ButterKnife.bind(this)

        val intent: Intent = intent

        category = intent.getStringExtra("category")

        Toast.makeText(this, category, Toast.LENGTH_LONG).show()

        val appComponent: AppComponent = DaggerAppComponent.builder().build()
        retrofit = appComponent.provideRetrofit()

        quizApiService = retrofit.create(QuizApiService::class.java)

        howManyPresenter = HowManyPresenter(this)



        fiveQuestion.setOnClickListener {
            onTextViewClicked(5)
        }
        tenQuestion.setOnClickListener {
            onTextViewClicked(10)
        }
        fifteenQuestion.setOnClickListener {
            onTextViewClicked(15)
        }
        twentyQuestion.setOnClickListener {
            onTextViewClicked(20)
        }


    }

    fun onTextViewClicked(count: Int) {
        howManyPresenter.onHowMany(quizApiService,category,count)
    }


    override fun onHowManyResult(questionList: List<Question>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onErrorResult(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

}
