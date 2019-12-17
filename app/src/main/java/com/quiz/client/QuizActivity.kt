package com.quiz.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.model.Question
import com.quiz.client.util.QuestionListKeeper
import kotlinx.android.synthetic.main.activity_quiz.*
import java.lang.IllegalArgumentException

class QuizActivity : AppCompatActivity() {

    lateinit var questionList:List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)


        questionList = QuestionListKeeper.questionListKeeper


        val rv =rv_top
        rv.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        rv.setHasFixedSize(true)

    }
}
