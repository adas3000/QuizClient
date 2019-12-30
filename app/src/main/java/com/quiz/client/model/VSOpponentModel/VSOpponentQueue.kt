package com.quiz.client.model.VSOpponentModel

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId
import com.quiz.client.util.hasInternetConnection

class VSOpponentQueue : IVSOpponentQueue {

    override fun hasInternetConn(context: Context): Boolean {
        return hasInternetConnection(context)
    }

    override fun getToken(context: Context): String {
        FirebaseApp.initializeApp(context)
        return FirebaseInstanceId.getInstance().id
    }


}