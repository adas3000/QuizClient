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
    lateinit var game_code:String

    companion object{
        @JvmStatic
        fun newInstance(iMultiQuizPresenter:IMultiQuizPresenter,game_code:String) = WaitFragment().apply {
            arguments = Bundle().apply {
                putString("game_code",game_code)
                putSerializable(WAITFRAGMENT_DESCRIBABLE_KEY,iMultiQuizPresenter)
            }
        }.apply {
            this.iMultiQuizPresenter = iMultiQuizPresenter
            this.game_code = game_code
        }
    }

    override fun onStart() {
        super.onStart()
        iMultiQuizPresenter.onCheckAllDevicesAnswered(game_code)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_wait,container,false)
    }

    fun doCheck() {
        iMultiQuizPresenter.onCheckAllDevicesAnswered(game_code)
    }

}