package com.quiz.client.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.quiz.client.R
import com.quiz.client.presenter.IMultiQuizPresenter

class WaitFragment: Fragment() {

    private val WAITFRAGMENT_DESCRIBABLE_KEY = "mquiz_act"

    lateinit var iMultiQuizPresenter: IMultiQuizPresenter


    companion object{
        @JvmStatic
        fun newInstance(iMultiQuizPresenter:IMultiQuizPresenter) = WaitFragment().apply {
            arguments = Bundle().apply {
                putSerializable(WAITFRAGMENT_DESCRIBABLE_KEY,iMultiQuizPresenter)
            }
        }.apply {
            this.iMultiQuizPresenter = iMultiQuizPresenter
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_wait,container,false)
    }

}