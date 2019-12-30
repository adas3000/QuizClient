package com.quiz.client.model.VSOpponentModel

import android.content.Context
import com.google.firebase.iid.FirebaseInstanceId
import com.quiz.client.util.hasInternetConnection
import com.quiz.client.util.getApplicationToken

class VSOpponentQueue : IVSOpponentQueue {

    override fun hasInternetConn(context: Context): Boolean {
        return hasInternetConnection(context)
    }

    override fun getToken(): String {
        return getApplicationToken()
    }


}