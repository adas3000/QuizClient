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
    lateinit var serial:String

     var value_ready:Boolean=false
     var value_answer:Boolean=false

    companion object{
        @JvmStatic
        fun newInstance(value_answer:Boolean,value_ready:Boolean) = StatsFragment().apply {
            arguments = Bundle().apply {
                putBoolean("value_answer",value_answer)
                putBoolean("value_ready",value_ready)
            }
        }.apply {
            this.value_ready = value_ready
            this.value_answer = value_answer
        }
    }


    override fun onStart() {
        super.onStart()
        rv_stats.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        rv_stats.setHasFixedSize(true)
        rv_stats.adapter = RecyclerViewStatsAdapter(scores)

        fstats_textView_goNext.setOnClickListener {
            multiQuizPresenter.onUpdateDevice(serial,value_answer,value_ready)
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fstats_layout,container,false)
    }

}