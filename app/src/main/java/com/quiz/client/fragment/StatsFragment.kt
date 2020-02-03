package com.quiz.client.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.R
import com.quiz.client.adapter.RecyclerViewStatsAdapter
import com.quiz.client.model.Score
import com.quiz.client.presenter.IMultiQuizPresenter
import com.quiz.client.util.getApplicationToken
import com.quiz.client.view.IMQuestionView
import com.quiz.client.view.IMultiQuizView
import kotlinx.android.synthetic.main.fstats_layout.*

class StatsFragment : Fragment() {

    lateinit var scores: List<Score>
    lateinit var multiQuizPresenter: IMultiQuizPresenter
    lateinit var serial: String
    lateinit var game_code: String

    var value_ready: Boolean = false
    var value_answer: Boolean = false
    var answer_was_correct: Boolean = false
    var quiz_finished: Boolean = false

    companion object {
        @JvmStatic
        fun newInstance(value_answer: Boolean, value_ready: Boolean, answer_was_correct: Boolean,
                        quiz_finished: Boolean,game_code:String) = StatsFragment().apply {
            arguments = Bundle().apply {
                putBoolean("value_answer", value_answer)
                putBoolean("value_ready", value_ready)
                putBoolean("answer_was_correct", answer_was_correct)
                putBoolean("quiz_finished", quiz_finished)
                putString("game_code",game_code)
            }
        }.apply {
            this.quiz_finished = quiz_finished
            this.value_ready = value_ready
            this.value_answer = value_answer
            this.answer_was_correct = answer_was_correct
            this.game_code = game_code
        }
    }


    override fun onStart() {
        super.onStart()
        rv_stats.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_stats.setHasFixedSize(true)
        rv_stats.adapter = RecyclerViewStatsAdapter(scores)

        fstats_layout_progressBar.visibility = View.INVISIBLE
        fstats_textView_goNext.visibility = View.VISIBLE

        if (!quiz_finished)
            fstats_textView_goNext.setOnClickListener {
                multiQuizPresenter.onUpdateDevice(serial, value_answer, value_ready)
                fstats_layout_progressBar.visibility = View.VISIBLE
                fstats_textView_goNext.visibility = View.INVISIBLE
            }
        else
            fstats_textView_goNext.setOnClickListener {
                multiQuizPresenter.onGameFinished(game_code)
            }

        var color = "#82DD55"

        if (!answer_was_correct) color = "#E23636"

        this.view!!.setBackgroundColor(Color.parseColor(color))
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fstats_layout, container, false)
    }

}