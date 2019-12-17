package com.quiz.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import com.quiz.client.component.AppComponent
import com.quiz.client.component.DaggerAppComponent
import com.quiz.client.presenter.HowManyPresenter
import com.quiz.client.service.QuizApiService
import com.quiz.client.view.IHowManyView
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
    }

    @OnClick(R.id.fiveQuestion, R.id.tenQuestion, R.id.fifteenQuestion, R.id.twentyQuestion)
    fun onTextViewClicked(view: View) {

        var count:Int = 0

        when(view.id){
            R.id.fiveQuestion -> count = 5
            R.id.tenQuestion -> count = 10
            R.id.fifteenQuestion -> count = 15
            R.id.twentyQuestion -> count = 20
        }



        howManyPresenter.onHowMany(quizApiService,category,5)
    }

    override fun onHowManyResult(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
