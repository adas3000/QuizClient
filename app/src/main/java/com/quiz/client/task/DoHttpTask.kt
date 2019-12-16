package com.quiz.client.task

import android.annotation.TargetApi
import android.os.AsyncTask
import com.quiz.client.service.QuizApiService
import retrofit2.Retrofit
import java.net.InetAddress

@TargetApi(25)
class DoHttpTask : AsyncTask<Void,Void,Boolean> {

    var retrofit:Retrofit

    constructor(retrofit:Retrofit){
        this.retrofit = retrofit
    }


    override fun doInBackground(vararg p0: Void?): Boolean {


        val quizApiService:QuizApiService = retrofit.create(QuizApiService::class.java)



        return true
    }
}