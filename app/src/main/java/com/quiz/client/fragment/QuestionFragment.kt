package com.quiz.client.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.QuizActivity
import com.quiz.client.R
import com.quiz.client.adapter.RecyclerViewAnswerAdapter
import com.quiz.client.adapter.RecyclerViewHeaderAdapter
import com.quiz.client.model.Question
import com.quiz.client.task.CountDownTask
import com.quiz.client.util.QuestionListKeeper
import com.quiz.client.view.IChoiceView
import com.quiz.client.view.IMQuestionView
import com.quiz.client.view.IMultiQuizParent
import kotlinx.android.synthetic.main.activity_quiz.*
import java.lang.IndexOutOfBoundsException

class QuestionFragment : Fragment(),IChoiceView,IMQuestionView {

    private val MQUIZ_DESCRIBABLE_KEY = "mquiz_act"

    var questionList:List<Question> = QuestionListKeeper.questionListKeeper

    lateinit var iMultiQuizParent:IMultiQuizParent

    lateinit var countDownTask: CountDownTask

    var allQuestionCount: Int = 0
    var correctCount: Int = 0

    var questionIndex:Int = -1

    var topHeaderList:MutableList<Int> = mutableListOf()

    companion object {
        @JvmStatic
        fun newInstance(questionIndex:Int, iMultiQuizParent: IMultiQuizParent,correctCount:Int,allQuestionCount:Int) = QuestionFragment().apply {
            arguments = Bundle().apply {
                putInt("questionIndex",questionIndex)
                putInt("correctCount",correctCount)
                putInt("allQuestionCount",allQuestionCount)
                putSerializable(MQUIZ_DESCRIBABLE_KEY,iMultiQuizParent)
            }
        }.apply {
            this.iMultiQuizParent = iMultiQuizParent
            this.questionIndex = questionIndex
            this.allQuestionCount = allQuestionCount
            this.correctCount = correctCount
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?  {

        return inflater.inflate(R.layout.activity_quiz,container,false)
    }

    override fun onStart() {
        super.onStart()
        setQuestionView(questionIndex)

    }

    fun setQuestionView(index:Int){

        if(index>=questionList.size || index<0){
            throw IndexOutOfBoundsException("wrong index value")
        }
        countDownTask = CountDownTask(textView_question_time_to_answer,this)
        countDownTask.execute(QuizActivity.TIME_TO_ANSWER)

        textView_question.text = questionList[index].value

        rv_choices.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        rv_choices.setHasFixedSize(true)

        rv_choices.adapter = RecyclerViewAnswerAdapter(questionList.get(index).choices!!.toList(),questionList.get(index).answer!!.correct!!.value,
            this)

        rv_top.layoutManager = GridLayoutManager(context,15)
        rv_top.setHasFixedSize(true)

        rv_top.adapter = RecyclerViewHeaderAdapter(questionList.size,topHeaderList)


          textView_question_count.setText(String.format(resources.getString(R.string.user_current_score_text),
              correctCount.toString(),allQuestionCount,toString()))
    }

    override fun setNextQuestion(correct: Boolean) {
        countDownTask.cancel(true)
        iMultiQuizParent.onNextQuestion(correct,countDownTask.timeRemaining)
    }


    override fun onCountDownFinish() {
        setNextQuestion(false)
    }



}