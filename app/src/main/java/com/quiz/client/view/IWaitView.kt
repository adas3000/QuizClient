package com.quiz.client.view

import com.quiz.client.presenter.IWaitPresenter

interface IWaitView {
    fun onSuccess(code:String)
    fun onError(msg:String)
    fun onContinue(code:String,iWaitPresenter: IWaitPresenter)
}