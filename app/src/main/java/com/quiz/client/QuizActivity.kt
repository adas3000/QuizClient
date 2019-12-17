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
import java.lang.IllegalArgumentException
import java.lang.IndexOutOfBoundsException

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

        setQuestionView(0)
    }

    override fun setNextQuestion(correct: Boolean) {

        if(correct){
            correctCount++
            Toast.makeText(this,"Good!",Toast.LENGTH_SHORT).show()
        }

        else {
            Toast.makeText(this,"Wrong:(",Toast.LENGTH_SHORT).show()
        }


    }

    fun setQuestionView(index:Int){

        if(index>=questionList.size || index<0){
            throw IndexOutOfBoundsException("wrong index value")
        }
        textView_question.text = questionList[index].value

        rv_choices.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        rv_choices.setHasFixedSize(true)

        rv_choices.adapter = RecyclerViewAnswerAdapter(questionList.get(index).choices!!.toList(),questionList.get(index).answer!!.correct!!.value,
            this)


    }


}
