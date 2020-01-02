package com.quiz.client.task

import android.os.AsyncTask
import android.widget.TextView
import java.lang.NullPointerException
import java.util.concurrent.TimeUnit

class CountDownTask : AsyncTask<Int,Int,Boolean> {

    val textViewToUpdate:TextView

    constructor(textViewToUpdate:TextView){
            this.textViewToUpdate = textViewToUpdate
    }

    override fun doInBackground(vararg p0: Int?): Boolean {

        var count = p0[0]
        if(count==null){
            throw NullPointerException("count is null")
        }

        while(count>0){
            TimeUnit.SECONDS.sleep(1)
            count--
            publishProgress(count)
        }
        return true
    }

    override fun onPreExecute() {

    }

    override fun onProgressUpdate(vararg values: Int?) {
        textViewToUpdate.setText(values[0]!!.toInt())
    }

    override fun onPostExecute(result: Boolean?) {
        
    }

}