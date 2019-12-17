package com.quiz.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.adapter.RecyclerViewAnswerAdapter
import com.quiz.client.adapter.RecyclerViewHeaderAdapter
import com.quiz.client.model.Question
import com.quiz.client.util.QuestionListKeeper
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    lateinit var questionList:List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)


        questionList = QuestionListKeeper.questionListKeeper


        val rv_top =rv_top
        rv_top.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        rv_top.setHasFixedSize(true)

        rv_top.adapter = RecyclerViewHeaderAdapter(questionList.size)

        textView_question.text = questionList[0].value

        val rv_chocies = rv_choices
        rv_chocies.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        rv_chocies.setHasFixedSize(true)

        rv_chocies.adapter = RecyclerViewAnswerAdapter(questionList.get(0).choices!!.toList())

    }
}
