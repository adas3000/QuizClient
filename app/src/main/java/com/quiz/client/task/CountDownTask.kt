package com.quiz.client.task

import android.os.AsyncTask
import android.widget.TextView
import com.quiz.client.R
import com.quiz.client.view.IMQuestionView
import kotlinx.android.synthetic.main.activity_quiz.view.*
import java.lang.NullPointerException
import java.util.concurrent.TimeUnit

class CountDownTask : AsyncTask<Int, Int, Boolean> {

    val textViewToUpdate: TextView
    val imQuestionView: IMQuestionView
    var timeRemaining:Int = 0

    constructor(textViewToUpdate: TextView, imQuestionView: IMQuestionView) {
        this.textViewToUpdate = textViewToUpdate
        this.imQuestionView = imQuestionView
    }

    override fun doInBackground(vararg p0: Int?): Boolean {

        var count = p0[0]
        if (count == null) {
            throw NullPointerException("count is null")
        }

        while (count > 0) {
            TimeUnit.SECONDS.sleep(1)
            count--
            publishProgress(count)
        }
        return true
    }

    override fun onCancelled() {
        textViewToUpdate.setText(R.string.time_to_answer_text)
    }

    override fun onPreExecute() {

    }

    override fun onProgressUpdate(vararg values: Int?) {
        textViewToUpdate.setText(values[0]!!.toString())
    }

    override fun onPostExecute(result: Boolean?) {
        imQuestionView.onCountDownFinish()
    }

}