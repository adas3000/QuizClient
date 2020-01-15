package com.quiz.client.presenter

import com.quiz.client.database.AppDatabase
import com.quiz.client.view.IUserView

class UserPresenter : IUserPresenter {

    val iUserView:IUserView
    val db: AppDatabase

    constructor(iUserView: IUserView,db: AppDatabase) {
        this.iUserView = iUserView
        this.db = db
    }


    override fun checkNickNameSet() {

        Thread(Runnable {
            val list = db.userDao().selectUser()
            if(list.size==0)
                iUserView.onBlankNickName()
        }).start()


    }

    override fun saveNickName(nick:String) {

    }
}