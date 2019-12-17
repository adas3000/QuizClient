package com.quiz.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.quiz.client.component.AppComponent
import com.quiz.client.component.DaggerAppComponent
import com.quiz.client.presenter.CategoryPresenter
import com.quiz.client.service.QuizApiService
import com.quiz.client.view.ICategoryView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity(),ICategoryView  {

    @Inject
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        val appComponent : AppComponent = DaggerAppComponent.builder().build()
        retrofit = appComponent.provideRetrofit()


        val quizApiService: QuizApiService = retrofit.create(QuizApiService::class.java)

        val rv = rv_Categories
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.setHasFixedSize(true)
        val categoryPresenter = CategoryPresenter(this,rv)
        categoryPresenter.onCategory(quizApiService)



    }

    override fun onCategoryResult(category: String) {

        val intent = Intent(this,SecondActivity::class.java).apply {
            putExtra("category",category)
        }
        startActivity(intent)
    }

}
