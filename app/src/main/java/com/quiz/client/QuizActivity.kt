package com.quiz.client

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.adapter.RecyclerViewAnswerAdapter
import com.quiz.client.adapter.RecyclerViewHeaderAdapter
import com.quiz.client.model.Question
import com.quiz.client.task.CountDownTask
import com.quiz.client.util.QuestionListKeeper
import com.quiz.client.view.IChoiceView
import com.quiz.client.view.IMQuestionView
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_quiz.*
import java.lang.IndexOutOfBoundsException

class QuizActivity : AppCompatActivity() , IChoiceView , IMQuestionView {

    val TIME_TO_ANSWER = 15

    lateinit var questionList:List<Question>
    lateinit var countDownTask:CountDownTask

    var allQuestionCount:Int=0
    var correctCount:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionList = QuestionListKeeper.questionListKeeper

        rv_top.layoutManager = GridLayoutManager(this,15)
        rv_top.setHasFixedSize(true)

        rv_top.adapter = RecyclerViewHeaderAdapter(questionList.size)

        for(str in questionList)
            println(str.value)

        setQuestionView(0)
    }

    override fun setNextQuestion(correct: Boolean) {

        var color:String = "#82DD55" // success color

        if(correct){



            countDownTask.cancel(true)
            correctCount++
            Toasty.success(this,"Good",Toasty.LENGTH_SHORT).show()
        }

        else {
            Toasty.error(this,"Wrong",Toasty.LENGTH_SHORT).show()
            color = "#E23636" // error color
        }
        rv_top.findViewHolderForAdapterPosition(allQuestionCount)?.itemView?.findViewById<TextView>(R.id.textView_square)?.setBackgroundColor(
            Color.parseColor(color))

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
        countDownTask = CountDownTask(textView_question_time_to_answer,this)
        countDownTask.execute(TIME_TO_ANSWER)

        textView_question.text = questionList[index].value

        rv_choices.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        rv_choices.setHasFixedSize(true)

        rv_choices.adapter = RecyclerViewAnswerAdapter(questionList.get(index).choices!!.toList(),questionList.get(index).answer!!.correct!!.value,
            this)


    }

    override fun onBackPressed() {
        val builder:AlertDialog.Builder = AlertDialog.Builder(this)
            .setMessage("Are you sure you wanna quit from quiz?You will loose all data from current quiz.")
            .setPositiveButton("Yes",{dialogInterface, i ->
                super.onBackPressed()
                finish()
            })
            .setNegativeButton("No",{dialogInterface,i->
                dialogInterface.cancel()
            })
            .setCancelable(false)
        builder.show()
    }

    override fun onCountDownFinish() {
        setNextQuestion(false)
    }

}
