package com.quiz.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.adapter.RecyclerViewAnswerAdapter
import com.quiz.client.adapter.RecyclerViewHeaderAdapter
import com.quiz.client.model.Question
import com.quiz.client.util.QuestionListKeeper
import com.quiz.client.view.IChoiceView
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() , IChoiceView {

    lateinit var questionList:List<Question>
    var allQuestionCount:Int=0
    var correctCount:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)


        questionList = QuestionListKeeper.questionListKeeper
        allQuestionCount = questionList.size


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

    override fun setNextQuestion(correct: Boolean) {

        allQuestionCount++
        if(correct){
            correctCount++
            Toast.makeText(this,"Good!",Toast.LENGTH_SHORT).show()
        }

        else {
            Toast.makeText(this,"Wrong:(",Toast.LENGTH_SHORT).show()
        }



    }

}
