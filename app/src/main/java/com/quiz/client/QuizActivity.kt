package com.quiz.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quiz.client.model.Question
import com.quiz.client.util.QuestionListKeeper
import java.lang.IllegalArgumentException

class QuizActivity : AppCompatActivity() {

    lateinit var questionList:List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)


        questionList = QuestionListKeeper.questionListKeeper




    }
}
