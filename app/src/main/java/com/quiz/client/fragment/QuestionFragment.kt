package com.quiz.client.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.FinishActivity
import com.quiz.client.R
import com.quiz.client.adapter.RecyclerViewAnswerAdapter
import com.quiz.client.model.Question
import com.quiz.client.task.CountDownTask
import com.quiz.client.util.QuestionListKeeper
import com.quiz.client.view.IChoiceView
import com.quiz.client.view.IMQuestionView
import com.quiz.client.view.IMultiQuizView
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_quiz.*
import java.lang.IndexOutOfBoundsException

class QuestionFragment : Fragment(),IChoiceView,IMQuestionView {

    private val MQUIZ_DESCRIBABLE_KEY = "mquiz_act"

    lateinit var questionList:List<Question>

    lateinit var iMultiQuizView:IMultiQuizView
    var questionIndex:Int = -1
    var timeRemaining = 0

    companion object {
        @JvmStatic
        fun newInstance(questionIndex:Int,iMultiQuizView: IMultiQuizView) = QuestionFragment().apply {
            arguments = Bundle().apply {
                putInt("questionIndex",questionIndex)
                putSerializable(MQUIZ_DESCRIBABLE_KEY,iMultiQuizView)
            }
        }.apply {
            this.iMultiQuizView = iMultiQuizView
            this.questionIndex = questionIndex
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?  {
        questionList = QuestionListKeeper.questionListKeeper
        setQuestionView(questionIndex)


        return inflater.inflate(R.layout.activity_quiz,container,false)
    }

    fun setQuestionView(index:Int){

        if(index>=questionList.size || index<0){
            throw IndexOutOfBoundsException("wrong index value")
        }
        textView_question.text = questionList[index].value

        rv_choices.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        rv_choices.setHasFixedSize(true)

        rv_choices.adapter = RecyclerViewAnswerAdapter(questionList.get(index).choices!!.toList(),questionList.get(index).answer!!.correct!!.value,
            this)

    }

    override fun setNextQuestion(correct: Boolean) {
        iMultiQuizView.onNextQuestion(correct,timeRemaining)
    }


    override fun onCountDownFinish() {
        Toasty.normal(context!!.applicationContext,"Time is up!",Toasty.LENGTH_SHORT).show()
    }



}