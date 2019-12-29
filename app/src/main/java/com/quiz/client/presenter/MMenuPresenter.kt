package com.quiz.client.presenter

import com.quiz.client.view.IMMenuView

class MMenuPresenter : IMMenuPresenter {

    val view:IMMenuView

    constructor(view: IMMenuView) {
        this.view = view
    }


    override fun onMMenuOption(which: Int) {
        view.onOptionsChoose(which)
    }
}