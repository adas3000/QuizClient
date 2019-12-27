package com.quiz.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.adapter.RecyclerViewAnswerAdapter
import com.quiz.client.adapter.RecyclerViewHeaderAdapter
import com.quiz.client.model.Question
import com.quiz.client.util.QuestionListKeeper
import com.quiz.client.view.IChoiceView
import kotlinx.android.synthetic.main.activity_quiz.*
import java.lang.IndexOutOfBoundsException

class QuizActivity : AppCompatActivity() , IChoiceView {

    lateinit var questionList:List<Question>
    var allQuestionCount:Int=0
    var correctCount:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)


        questionList = QuestionListKeeper.questionListKeeper

        rv_top.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        rv_top.setHasFixedSize(true)

        rv_top.adapter = RecyclerViewHeaderAdapter(questionList.size)

        for(str in questionList)
            println(str.value)

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

        allQuestionCount++

        textView_question_count.setText(correctCount.toString()+"/"+allQuestionCount.toString())

        if(allQuestionCount==questionList.size){


            val intent = Intent(this,FinishActivity::class.java).apply{
                putExtra("score",correctCount.toString())
                putExtra("all",allQuestionCount.toString())
            }

            startActivity(intent)
            finish()
        }

        else setQuestionView(allQuestionCount)

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

    override fun onBackPressed() {
        Toast.makeText(this,"ioasdjiaosd",Toast.LENGTH_SHORT).show()

        val builder:AlertDialog.Builder = AlertDialog.Builder(this)
            .setMessage("Are you sure you wanna quit from quiz?")
            .setPositiveButton("Yes",{dialogInterface, i ->
                super.onBackPressed()
                finish()
            })
            .setNegativeButton("No",{dialogInterface,i->
                dialogInterface.cancel()
            })

        builder.show()
    }


}
