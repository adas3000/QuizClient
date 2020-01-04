package com.quiz.client.fragment

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

    lateinit var scores:List<Score>
    lateinit var multiQuizPresenter:IMultiQuizPresenter
    lateinit var game_code:String

    override fun onStart() {
        super.onStart()
        rv_stats.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        rv_stats.setHasFixedSize(true)
        rv_stats.adapter = RecyclerViewStatsAdapter(scores)

        fstats_textView_goNext.setOnClickListener {
            multiQuizPresenter.onNewQuestionCheck(game_code)
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fstats_layout,container,false)
    }

}