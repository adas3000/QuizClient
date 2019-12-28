package com.quiz.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnTouch
import com.quiz.client.adapter.RecyclerViewCountSelectAdapter
import com.quiz.client.component.AppComponent
import com.quiz.client.component.DaggerAppComponent
import com.quiz.client.model.Question
import com.quiz.client.presenter.HowManyPresenter
import com.quiz.client.service.QuizApiService
import com.quiz.client.util.QuestionListKeeper
import com.quiz.client.view.IHowManyView
import kotlinx.android.synthetic.main.activity_second.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
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

        val questionCountSet:TreeSet<Int> = sortedSetOf(5,10,15,20,25,30)

        val appComponent: AppComponent = DaggerAppComponent.builder().build()
        retrofit = appComponent.provideRetrofit()

        quizApiService = retrofit.create(QuizApiService::class.java)

        howManyPresenter = HowManyPresenter(this)

        val rv = rv_questionCount_2
        rv.layoutManager = GridLayoutManager(this,2)
        rv.setHasFixedSize(true)
        rv.adapter = RecyclerViewCountSelectAdapter(questionCountSet,howManyPresenter,quizApiService,category)



    }

    fun onTextViewClicked(count: Int) {
        howManyPresenter.onHowMany(quizApiService,category,count)
    }


    override fun onHowManyResult(questionList: List<Question>) {

        QuestionListKeeper.questionListKeeper = questionList

        val intent = Intent(this,QuizActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onErrorResult(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

}
